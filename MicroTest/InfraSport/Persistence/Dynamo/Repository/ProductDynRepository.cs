using Amazon.DynamoDBv2.Model;
using AppSport.Persistence.Repository;
using Domain.Entities;
using InfraSport.Persistence.Dynamo.Mapping;
using Infrastructure.Sql.Dynamo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Dynamo.Repository;

public class ProductDynRepository : IProductRepository
{
	private readonly IDynamoDBAccess _db;
	public ProductDynRepository(IDynamoDBAccess db)
    {
        _db = db;
    }
    public async Task<bool> DeleteAsync(long id)
	{
		return await _db.DeleteAsync<ProductDyn, long>(id);
	}

	public async Task<IEnumerable<Product>> GetAllAsync()
	{
		return await _db.QueryAllAsync<ProductDyn>("Product");
	}

	public async Task<Product?> GetAsync(long id)
	{
		return await _db.QueryAsync<ProductDyn, long>(id);
	}

	public async Task<IEnumerable<string>> GetCategories()
	{
		var products = await _db.QueryAllAsync<ProductDyn>("Product");
		return products.Select(x => x.Category).Distinct();
	}

	public async Task<int> GetMaxAsync(string? category)
	{
		ScanRequest request = new ScanRequest("SportStore")
		{
			Select = "COUNT",
		};
		if (category != null)
		{
			request.FilterExpression = $"Category = :seek";
			request.ExpressionAttributeValues = new()
			{
				{ ":seek", new AttributeValue { S = category } }
			};
		}
		ScanResponse? response = await _db.ScanAsync<ProductDyn>(request, "Product");
		return response != null ? response.Count : 0;
	}

	public async Task<IEnumerable<Product>> GetPagedAsync(int nr, int amount, string? category)
	{
		ScanRequest request = new ScanRequest("SportStore");

		if (category != null)
		{
			request.FilterExpression = $"Category = :seek";
			request.ExpressionAttributeValues = new()
			{
				{ ":seek", new AttributeValue { S = category } }
			};
		}
		return await _db.ScanAsyncPaginated<Product>(request, "Product", nr, amount, 
			x => new Product(long.Parse(x["ID"].N), x["Name"].S, x["Description"].S, decimal.Parse(x["Price"].N), x["Category"].S));
	}

	public async Task<Product?> InsertAsync(Product product)
	{
		ProductDyn productDyn = new ProductDyn(product)
		{
			ProductID = new Random().Next(1000000)
		};
		return await _db.InsertOrUpdateAsync<ProductDyn>(productDyn) ? productDyn : null;
	}

	public async Task<bool> UpdateAsync(Product product)
	{
		ProductDyn prdouctDyn = new ProductDyn(product);
		return await _db.InsertOrUpdateAsync(prdouctDyn);
	}
}
