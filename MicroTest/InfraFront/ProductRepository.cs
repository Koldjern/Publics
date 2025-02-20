using AppSport.Persistence.Repository;
using Domain.Entities;
using Infrastructure.Api;
using Infrastructure.Sql.Dapper;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraFront;

public class ProductRepository : IProductRepository
{
    private readonly IApiAccess _apiAccess;
    public string? Token;
    public ProductRepository(IApiAccess apiAccess, IConfiguration configuration)
    {
        _apiAccess = apiAccess;
        _apiAccess.Configure(configuration, "SportStore");
    }
    public async Task<bool> DeleteAsync(long id)
    {
        return await SendToApi(_apiAccess.DeleteData<bool>($"Products/Id/{id}"), () => false);
    }

    public async Task<IEnumerable<Product>> GetAllAsync()
    {
        return await SendToApi(_apiAccess.QueryMultiple<Product>($"Products"), () => []);
    }

    public async Task<Product?> GetAsync(long id)
    {
        return await SendToApi(_apiAccess.QuerySingle<Product>($"Products/Id/{id}"), () => null);
    }

    public async Task<IEnumerable<string>> GetCategories()
    {
        return await SendToApi(_apiAccess.QueryMultiple<string>("Products/Categories"), () => []);
    }

    public async Task<int> GetMaxAsync(string? category)
    {
        return await SendToApi(_apiAccess.QuerySingle<int>($"Products/Max/Category/{category}"), () => 0);
    }

    public async Task<IEnumerable<Product>> GetPagedAsync(int nr, int amount, string? category)
    {
        return await SendToApi(_apiAccess.QueryMultiple<Product>($"Products/Nr/{nr}/Amount/{amount}/Category/{category}", Token ?? ""), () => []);
	}

    public async Task<Product?> InsertAsync(Product product)
    {
        return await SendToApi(_apiAccess.PostData<Product, Product>("Products", product), () => null);
    }

    public async Task<bool> UpdateAsync(Product product)
    {
        return await SendToApi(_apiAccess.UpdateData<bool, Product>("Products", product), () => false);
    }
    private async Task<T> SendToApi<T>(Task<T> send, Func<T> defaultVal)
    {
        try
        {
            return await send;
        }
        catch (Exception ex)
        {
            return defaultVal();
        }
    }
}
