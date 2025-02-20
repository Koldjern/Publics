using LogicLayer;
using PresentationLayer.Models.Errors;
using PresentationLayer.Models.Session;

namespace PresentationLayer;

public class Program
{
	public static void Main(string[] args)
	{
		var builder = WebApplication.CreateBuilder(args);
		{
			builder.Services.AddLogic(builder.Configuration, builder.Environment.IsDevelopment());
			builder.Services.AddControllersWithViews();
			builder.Services.AddControllersWithViews(options =>
			{
				options.Filters.Add<ExceptionFilter>();
			});

			#region Sessions Injections
			builder.Services.AddSingleton<IHttpContextAccessor, HttpContextAccessor>();
			builder.Services.AddDistributedMemoryCache();
			builder.Services.AddSession();
			builder.Services.AddScoped<SessionData>();
			#endregion
        }
        var app = builder.Build();
		{
			app.UseSession();
			app.UseHttpsRedirection();
			app.AddLogicBuild(builder.Environment.IsDevelopment(), builder.Configuration); 
			app.UseStaticFiles();
			app.UseRouting();
			app.UseAuthentication();
			app.UseAuthorization();
			app.AddUrls();
			app.Run();
		}
	}
}
