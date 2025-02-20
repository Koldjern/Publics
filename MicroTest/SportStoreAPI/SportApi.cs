using AppSport.Persistence.Repository;
using InfraSport;
using InfraSport.Persistence.EF;
using InfraSport.Persistence.Repository;
using Infrastructure.Sql.EF;
using InfraSport.Migrations;
using Infrastructure.Auth;

namespace SportStoreAPI;

public class SportApi
{
	public static void Main(string[] args)
	{
		var builder = WebApplication.CreateBuilder(args);
		{
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddInfrastructure(builder.Configuration);
			builder.Services.SetupJwtAuth(builder.Configuration);

			builder.Services.AddAuthorization();
		}
		var app = builder.Build();
		{
			//app.EnsurePopulated();
			app.UseAuthentication();
			app.UseAuthorization();
			app.UseHttpsRedirection();
			app.ConfigureApiProduct();
			app.ConfigureApiOrder();
			app.Run();
		}
	}
}
