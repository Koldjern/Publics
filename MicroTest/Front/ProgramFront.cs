using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Domain.Entities;
using Front.Models.Common;
using InfraFront;
using Infrastructure.Api;
using Infrastructure.Auth;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Identity;
using Microsoft.IdentityModel.Tokens;
using System.Security.Cryptography;
using System.Text;

namespace Front;

public class ProgramFront
{
	public static void Main(string[] args)
	{
		var builder = WebApplication.CreateBuilder(args);
		{
			builder.Services.AddInfrastructure();
			builder.Services.AddControllersWithViews();
			builder.Services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();

			builder.Services.AddRazorPages();
			builder.Services.AddDistributedMemoryCache();
			builder.Services.AddSession();
			builder.Services.AddScoped<SessionHolder<Cart>>
				(sp => new(sp.GetRequiredService<IHttpContextAccessor>(), () => new Cart()));
			builder.Services.AddServerSideBlazor();
			ConfigureServices(builder.Services, builder.Configuration);
		}

		var app = builder.Build();
		{
			app.UseHttpsRedirection();
			app.UseStaticFiles();
			app.UseSession();
			app.UseRouting();
			app.UseAuthentication(); // Must come before UseAuthorization
			app.UseAuthorization();
			app.AddPaths();
			app.MapDefaultControllerRoute();
			app.MapRazorPages();
			app.MapBlazorHub();
			app.MapFallbackToPage("/admin/{*catchall}", "/Admin/Index");
			app.Run();
		}

	}
	public static void ConfigureServices(IServiceCollection services, IConfiguration configuration)
	{
		var jwtSettings = configuration.GetSection("Jwt");
		var rsa = RSA.Create();
		rsa.ImportSubjectPublicKeyInfo(Convert.FromBase64String(jwtSettings["PublicKey"]), out _);
		services.AddAuthentication(options =>
		{
			options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
			options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
		})
		.AddJwtBearer(options =>
		{
			options.TokenValidationParameters = new TokenValidationParameters
			{
				ValidateIssuer = true,
				ValidateAudience = true,
				ValidateLifetime = true,
				ValidateIssuerSigningKey = true,
				ValidIssuer = jwtSettings["Issuer"],
				ValidAudience = jwtSettings["Audience"],
				IssuerSigningKey = new RsaSecurityKey(rsa)
			};

			// Custom handling to read token from cookie
			options.Events = new JwtBearerEvents
			{
				OnMessageReceived = context =>
				{
					context.Token = context.HttpContext.Request.Cookies["AuthToken"];
					return Task.CompletedTask;
				}
			};
		});
		services.AddControllersWithViews();
	}
}
