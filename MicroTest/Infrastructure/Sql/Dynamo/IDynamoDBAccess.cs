using Amazon.DynamoDBv2.DataModel;
using Amazon.DynamoDBv2.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Sql.Dynamo;

public interface IDynamoDBAccess
{
	Task<bool> InsertOrUpdateAsync<T>(T item);
	Task<T?> QueryAsync<T, U>(U id);
	Task<bool> DeleteAsync<T, U>(U id);
	Task<IEnumerable<T>> QueryAllAsync<T>(string type);
	Task<IEnumerable<T>> QueryAllAsync<T>(List<ScanCondition> conditions, string type);
	Task<ScanResponse?> ScanAsync<T>(ScanRequest request, string type);
	Task<List<T>> ScanAsyncPaginated<T>(ScanRequest request, string type, int nr, int amount, Func<Dictionary<string, AttributeValue>, T> ctr);


}
