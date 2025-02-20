using InfraStudent;
using InfraStudent.Migrations;

namespace StudentAPI;

	public class StudentApi
	{
		public static void Main(string[] args)
		{
        var builder = WebApplication.CreateBuilder(args);
        {
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddInfrastructure(builder.Configuration);
        }
        var app = builder.Build();
        {
            app.UseHttpsRedirection();
            app.ConfigureApi();
            //app.EnsurePopulated();
            app.Run();
        }
    }
	}
