using Application;
using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Infrastructure.Api;
using Infrastructure.Auth;
using Infrastructure.Sql.Dynamo;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraFront;

public static class DependencyInjection
{
	public static IServiceCollection AddInfrastructure(this IServiceCollection services)
	{
		services.AddTransient<IApiAccess, ApiAccess>();
		services.AddScoped<IIdentityRepository, IdentityRepository>();
		services.AddScoped<IProductRepository, ProductRepository>();
		services.AddScoped<IStudentRepository, StudentRepository>();
		services.AddScoped<IOrderRepository, OrderRepository>();
		return services;
	}
}
