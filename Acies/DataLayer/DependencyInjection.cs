using DataLayer.EF.Context;
using DataLayer.EF.Migrations.Auth;
using DataLayer.EF.Migrations.PalletMigration;
using DataLayer.Files.Settings;
using DataLayer.Files.TypeReaders;
using DataLayer.Orders;
using DataLayer.Pallets;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Models.Entities.Settings;

namespace DataLayer;

public static class DependencyInjection
{
	public static IServiceCollection AddData(this IServiceCollection services, IConfiguration configuration, bool inDevelopment)
	{
		//settings and a type reader
		services.AddSingleton<ITypeHandler, DataTranslater>();
		services.AddSingleton<ISettingsRepository, LocalSettingsRepository>();
		services.AddTransient<PlacementSettings>(provider => provider.GetService<ISettingsRepository>()!.GetSettings()!.PlacementSettings);
		
		//adds identity db
		services.AddDbContext<AuthContext>(options =>
			options.UseSqlServer(
			configuration[!inDevelopment ? "ConnectionStrings:IdentityConnection" : "ConnectionStrings:LocalIdentity"]));
		//adds palletDB
		services.AddDbContext<PalletContext>(options =>
			options.UseSqlServer(
			configuration[!inDevelopment ? "ConnectionStrings:IdentityConnection" : "ConnectionStrings:LocalPallet"]));
		//wanna add the IpalletContext to controllers do tests and such,  
		services.AddScoped<IPalletContext>(provider => provider.GetService<PalletContext>()!);
		//rules for identity
		services.AddIdentity<IdentityUser, IdentityRole>(options =>
		{
			options.Password.RequireDigit = true;
			options.Password.RequiredLength = 8;
			options.Password.RequireNonAlphanumeric = false;
		}).AddEntityFrameworkStores<AuthContext>();

		services.AddScoped<PalletRepository, EFPalletRepository>();
		services.AddScoped<IOrderRepository, LocalRepository>();

            services.AddDbAndPallets(inDevelopment);
		
        return services;
	}
    public static void AddDataBuild(this WebApplication app, bool inDevelopment, IConfiguration configuration)
    {

			SeedLogin.EnsurePopulated(app);
    }
}
