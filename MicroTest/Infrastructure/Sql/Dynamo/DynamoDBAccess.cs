using Amazon.DynamoDBv2.DataModel;
using Amazon.DynamoDBv2;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Amazon.Runtime;
using Amazon.DynamoDBv2.DocumentModel;
using Amazon.DynamoDBv2.Model;
using Microsoft.Extensions.Configuration;
using Amazon.Runtime.Internal;
using static System.Net.Mime.MediaTypeNames;

namespace Infrastructure.Sql.Dynamo;

public class DynamoDBAccess : IDynamoDBAccess
{
	private readonly IAmazonDynamoDB _dynamoDbClient;
	private readonly DynamoDBContext _context;
	public DynamoDBAccess(IConfiguration configuration)
	{
		string accessKey = configuration["Accesskey"]!;
		string secretKey = configuration["SecretAccessKey"]!;
		string region = configuration["AWSRegion"]!;
		var credentials = new BasicAWSCredentials(accessKey, secretKey);
		var config = new AmazonDynamoDBConfig
		{
			RegionEndpoint = Amazon.RegionEndpoint.GetBySystemName(region)
		};

		_dynamoDbClient = new AmazonDynamoDBClient(credentials, config);
		_context = new DynamoDBContext(_dynamoDbClient);
	}
	public async Task<bool> InsertOrUpdateAsync<T>(T item)
	{
		try
		{
			await _context.SaveAsync(item);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public async Task<T?> QueryAsync<T, U>(U id)
	{
		try
		{
			return await _context.LoadAsync<T>(id);
		}
		catch (Exception e)
		{
			return default;
		}
	}

	public async Task<bool> DeleteAsync<T, U>(U id)
	{
		try
		{
			await _context.DeleteAsync<T>(id);
			return true;
		}
		catch (Exception e)
		{
			return default;
		}
	}
	public async Task<ScanResponse?> ScanAsync<T>(ScanRequest request, string type)
	{
		try
		{
			request.AddType(type);
			return await _dynamoDbClient.ScanAsync(request);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	public async Task<List<T>> ScanAsyncPaginated<T>(ScanRequest request, string type, int nr, int amount, Func<Dictionary<string, AttributeValue>, T> ctr)
	{
		try
		{
			request.AddType(type);
			return await ScanningWhile(request, nr, amount, ctr);
		}
		catch (Exception e)
		{
			return [];
		}
	}
	public async Task<IEnumerable<T>> QueryAllAsync<T>(string type)
	{
		try
		{
			AsyncSearch<T> search = _context.ScanAsync<T>(new List<ScanCondition>().AddType(type));
			List<T> result = await search.GetNextSetAsync();
			List<T> next;
			while ((next = await search.GetNextSetAsync()).Count != 0)
				result.AddRange(next);
			return result;
		}
		catch (Exception e)
		{
			return [];
		}
	}
	public async Task<IEnumerable<T>> QueryAllAsync<T>(List<ScanCondition> conditions, string type)
	{
		try
		{
			return await _context.ScanAsync<T>(conditions.AddType(type)).GetRemainingAsync();
		}
		catch (Exception e)
		{
			return [];
		}
	}
	private async Task<List<T>> ScanningWhile<T>(ScanRequest scan, int nr, int amount, Func<Dictionary<string, AttributeValue>, T> ctr)
	{
		ScanResponse response = await _dynamoDbClient.ScanAsync(scan);
		Dictionary<string, AttributeValue>? lastEvaluatedKey = response.LastEvaluatedKey;

		while (lastEvaluatedKey.Count != 0 || lastEvaluatedKey.Count >= nr)
		{
			scan.ExclusiveStartKey = lastEvaluatedKey;
			response = await _dynamoDbClient.ScanAsync(scan);
			lastEvaluatedKey = response.LastEvaluatedKey;
		}

		List<T> result = response.Items.Select(x => ctr(x)).ToList();
		while (lastEvaluatedKey.Count != 0 || result.Count >= amount)
		{
			response = await _dynamoDbClient.ScanAsync(scan);
			lastEvaluatedKey = response.LastEvaluatedKey;
			response.Items.ForEach(x => 
			{ 
				if (result.Count < amount) 
					result.Add(ctr(x)); 
			});
		}
		return result;
	}
}
public static class DynExs
{
	public static List<ScanCondition> AddType(this List<ScanCondition> conditions, string type)
	{
		conditions ??= new List<ScanCondition>();
		conditions.Add(new ScanCondition("Entity", ScanOperator.Equal, type));
		return conditions;
	}
	public static ScanRequest AddType(this ScanRequest request, string type)
	{
		if (request.FilterExpression != null)
			request.FilterExpression += $" AND Entity = :type";
		else
			request.FilterExpression = "Entity = :type";
		request.ExpressionAttributeValues ??= new Dictionary<string, AttributeValue>();
		request.ExpressionAttributeValues[":type"] = new() { S = type };
		return request;
	}

}
