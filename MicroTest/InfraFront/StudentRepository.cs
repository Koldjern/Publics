using AppStudent.Persistence.Repository;
using Domain.Entities;
using Infrastructure.Api;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraFront;

public class StudentRepository : IStudentRepository
{
	private readonly IApiAccess _apiAccess;
	public StudentRepository(IApiAccess apiAccess, IConfiguration configuration)
	{
		_apiAccess = apiAccess;
		_apiAccess.Configure(configuration, "Students");
	}
	public async Task<bool> AddStudent(Student student)
	{
		return await SendToApi(_apiAccess.PostData<bool, Student>("Students", student), () => false);
	}

	public async Task<bool> DeleteStudent(string email)
	{
		return await SendToApi(_apiAccess.DeleteData<bool>($"Students/Email/{email}"), () => false);
	}

	public async Task<IEnumerable<string>> GetEducations()
	{
		return await SendToApi(_apiAccess.QueryMultiple<string>($"Students/Educations"), () => []);
	}

	public async Task<int> GetMaxAsync(string? education)
	{
		return await SendToApi(_apiAccess.QuerySingle<int>($"Students/Max/Education/{education}"), () => 0);
	}

	public async Task<IEnumerable<Student>> GetPagedAsync(int nr, int amount, string? education)
	{
		return await SendToApi(_apiAccess.QueryMultiple<Student>($"Students/Nr/{nr}/Amount/{amount}/Education/{education}"), () => []);
	}

	public async Task<Student?> StudentByEmail(string email)
	{
		return await SendToApi(_apiAccess.QuerySingle<Student>($"Students/Email/{email}"), () => null);
	}

	public async Task<IEnumerable<Student>> GetStudents()
	{
		return await SendToApi(_apiAccess.QueryMultiple<Student>("Students"), () => []);
	}

	public async Task<bool> UpdateStudent(Student student)
	{
		return await SendToApi(_apiAccess.UpdateData<bool, Student>("Students", student), () => false);
	}
	private async Task<T> SendToApi<T>(Task<T> send, Func<T> defaultVal)
	{
		try
		{
			return await send;
		}
		catch (Exception ex)
		{
			return defaultVal();
		}
	}
}
