using AppSport.Persistence.Repository;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace SportStoreAPI;

public static class ProductEnds
{
	public static void ConfigureApiProduct(this WebApplication app)
	{
		app.MapGet("Products/Nr/{nr}/Amount/{amount}/Category/{category?}", GetPagedAsync);
		app.MapGet("Products", GetAllAsync);
		app.MapDelete("Products(id/{id}", DeleteProduct);
		app.MapPost("Products", PostAsync);
        app.MapGet("Products/Id/{id}", GetAsync);
        app.MapGet("Products/Categories", GetCategories);
        app.MapPut("Products", UpdateAsync);
		app.MapGet("Products/Max/Category/{category?}", GetMaxAsync);

	}
	private async static Task<IResult> UpdateAsync(Product product, IProductRepository productRepository)
    {
        return await Act(productRepository.UpdateAsync(product), x => x);
    }
    private async static Task<IResult> GetMaxAsync(string? category, IProductRepository productRepository)
    {
        return await Act(productRepository.GetMaxAsync(category), x => x >= 0);
    }
    private async static Task<IResult> GetCategories(IProductRepository productRepository)
    {
        return await Act(productRepository.GetCategories(), x => x != null);
    }
    private async static Task<IResult> GetAsync(long id, IProductRepository productRepository)
    {
        return await Act(productRepository.GetAsync(id), x => x != null);
    }
    private async static Task<IResult> PostAsync(Product product, IProductRepository productRepository)
    {
        return await Act(productRepository.InsertAsync(product), x => x != null);
    }
    [Authorize]
    private async static Task<IResult> GetPagedAsync(int nr, int amount, string? category, IProductRepository productRepository) 
	{
		return await Act(productRepository.GetPagedAsync(nr, amount, category), x => x != null);
	}
    private async static Task<IResult> GetAllAsync(IProductRepository productRepository)
    {
        return await Act(productRepository.GetAllAsync(), x => x != null);
    }
	private async static Task<IResult> DeleteProduct(long id, IProductRepository productRepository)
	{
		return await Act(productRepository.DeleteAsync(id), (bool x) => x);
	}
	private static async Task<IResult> Act<T>(Task<T> getter, Func<T,bool> condition)
	{
        try
        {
            var results = await getter;
            return condition(results) ? Results.Ok(results) : Results.NotFound(false);
        }
        catch (Exception ex)
        {
            return Results.Problem(ex.Message);
        }
    }
}

