using AppSport.Persistence.Repository;
using Domain.Entities;

namespace SportStoreAPI;

public static class OrderEnds
{
	public static void ConfigureApiOrder(this WebApplication app)
	{
		app.MapGet("Orders", GetAllAsync);
		app.MapPost("Orders", PostAsync);
		app.MapGet("Orders/id/{id}", GetAsync);
		app.MapPut("Orders", UpdateAsync);
	}
	private async static Task<IResult> UpdateAsync(Order order, IOrderRepository repo)
	{
		return await Act(repo.EditOrder(order), x => x != null);
	}

	private async static Task<IResult> PostAsync(Order order, IOrderRepository repo)
	{
		return await Act(repo.AddOrder(order), x => x != null);
	}

	private async static Task<IResult> GetAllAsync(IOrderRepository repo)
	{
		return await Act(repo.Orders(), x => x != null);
	}
	private async static Task<IResult> GetAsync(int id, IOrderRepository repo)
	{
		return await Act(repo.FindOrder(id), x => x != null);
	}
	private static async Task<IResult> Act<T>(Task<T> getter, Func<T, bool> condition)
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
