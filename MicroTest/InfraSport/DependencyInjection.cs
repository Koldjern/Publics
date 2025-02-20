using AppSport.Persistence.Repository;
using InfraSport.Persistence.Dynamo.Repository;
using InfraSport.Persistence.EF;
using InfraSport.Persistence.Repository;
using Infrastructure.Sql.Dapper;
using Infrastructure.Sql.Dynamo;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace InfraSport;
public static class DependencyInjection
{
	public static IServiceCollection AddInfrastructure(this IServiceCollection services, IConfiguration configuration)
	{
		//services.AddDbContext<SportEFContext>(opts =>
		//{
		//	opts.UseSqlServer(
		//	configuration["ConnectionStrings:SportsStoreConnection"]);
		//});
		//services.AddScoped<ISportEFContext>(provider => provider.GetService<SportEFContext>()!);
		//services.AddScoped<IProductRepository, ProductRepository>();
		//services.AddScoped<IOrderRepository, OrderRepository>();


		services.AddSingleton<IDynamoDBAccess, DynamoDBAccess>();
		services.AddScoped<IProductRepository, ProductDynRepository>();
		services.AddScoped<IOrderRepository, OrderDynRepository>();
		return services;
	}
}
