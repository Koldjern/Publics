using Infrastructure.Auth;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System.Security.Cryptography;
using System.Text;

namespace AuthAPI;

public class Program
{
	public static void Main(string[] args)
	{
		var builder = WebApplication.CreateBuilder(args);
		{
			builder.Services.AddDbContext<AppIdentityDbContext>(
				opts => opts.UseSqlServer(builder.Configuration.GetConnectionString("IdentityConnection"))
			);

			builder.Services.AddIdentity<IdentityUser, IdentityRole>()
				.AddEntityFrameworkStores<AppIdentityDbContext>()
				.AddDefaultTokenProviders();
			PrivateSetupJwtAuth(builder.Services, builder.Configuration);

			builder.Services.AddAuthorization();
			builder.Services.AddControllers();
			builder.Services.AddEndpointsApiExplorer();
		}

		var app = builder.Build();
		{
			SeedData.EnsurePopulated(app);
			app.UseAuthentication();
			app.UseAuthorization();
			app.MapControllers();
			app.UseHttpsRedirection();
			app.Run();
		}
	}
	public static void PrivateSetupJwtAuth(IServiceCollection services, IConfiguration configuration)
	{
		var jwtSettings = configuration.GetSection("Jwt");
		var rsa = RSA.Create();
		rsa.ImportRSAPrivateKey(Convert.FromBase64String(jwtSettings["PrivateKey"]), out _);
		services.AddAuthentication(options =>
		{
			options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
			options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
		}).AddJwtBearer(options =>
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
		});
	}
}
