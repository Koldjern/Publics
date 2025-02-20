using AppStudent.Persistence.Repository;
using Infrastructure.Sql.Dapper;
using Infrastructure.Sql.Dynamo;
using InfraStudent.Persistence.Dynamo.Repository;
using InfraStudent.Persistence.EF;
using InfraStudent.Persistence.Repository;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace InfraStudent;

public static class DependencyInjection
{
	public static IServiceCollection AddInfrastructure(this IServiceCollection services, IConfiguration configuration)
	{
		//services.AddDbContext<StudentEFContext>(opts =>
		//{
		//	opts.UseSqlServer(
		//	configuration["ConnectionStrings:Students"]);
		//});
		//services.AddScoped<IStudentEFContext>(provider => provider.GetService<StudentEFContext>()!);
		//services.AddScoped<IStudentRepository, StudentRepository>();
		services.AddSingleton<IDynamoDBAccess, DynamoDBAccess>();
		services.AddScoped<IStudentRepository, StudentDynRepository>();
		return services;
	}
}
