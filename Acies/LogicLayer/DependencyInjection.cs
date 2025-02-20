using DataLayer;
using LogicLayer.Auth;
using LogicLayer.Orders;
using LogicLayer.Packing;
using LogicLayer.Packing.Decorations;
using LogicLayer.pallet;
using LogicLayer.Settings;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer;

public static class DependencyInjection
{
	public static IServiceCollection AddLogic(this IServiceCollection services, IConfiguration configuration, bool inDevelopment)
	{
		services.AddData(configuration, inDevelopment);
		services.AddScoped<ISettingsService, SettingsService>();
		services.AddScoped<IAuthService, AuthService>();
		services.AddScoped<IPalletService, PalletService>();
		services.AddScoped<IOrderService, OrderService>();
		services.AddScoped<IPackingService, PackingService>();
		services.AddScoped<IPackingMediator, PackingMediator>();

        return services;
	}
	public static void AddLogicBuild(this WebApplication app, bool inDevelopment, IConfiguration configuration)
	{
		app.AddDataBuild(inDevelopment, configuration);	
	}
}
