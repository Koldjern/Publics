using Amazon.DynamoDBv2.Model;
using AppStudent.Persistence.Repository;
using Domain.Entities;
using Infrastructure.Sql.Dynamo;
using InfraStudent.Persistence.Dynamo.Mapping;
using System.Collections.Generic;

namespace InfraStudent.Persistence.Dynamo.Repository;

public class StudentDynRepository : IStudentRepository
{
	private readonly IDynamoDBAccess _db;
	public StudentDynRepository(IDynamoDBAccess db)
	{
		_db = db;
	}

	public async Task<bool> AddStudent(Student student)
	{
		StudentDyn studentDyn = new StudentDyn(student);
		return await _db.InsertOrUpdateAsync<StudentDyn>(studentDyn);
	}

	public async Task<bool> DeleteStudent(string email)
	{
		return await _db.DeleteAsync<StudentDyn, string>(email);
	}

	public async Task<IEnumerable<string>> GetEducations()
	{
		var students = await _db.QueryAllAsync<StudentDyn>("Student");
		return students.Select(x => x.Education).Distinct();
	}

	public async Task<int> GetMaxAsync(string? education)
	{
		ScanRequest request = new ScanRequest("TestStudents")
		{
			Select = "COUNT",
		};
		if (education != null)
		{
			request.FilterExpression = $"Education = :v_Education";
			request.ExpressionAttributeValues = new()
			{
				{ ":v_Education", new AttributeValue { S = education } }
			};
		}
		ScanResponse? response = await _db.ScanAsync<StudentDyn>(request, "Student");
		return response != null ? response.Count : 0;
	}

	public async Task<IEnumerable<Student>> GetPagedAsync(int nr, int amount, string? education)
	{
		ScanRequest request = new ScanRequest("TestStudents")
		{
			Limit = amount,

		};
		if (education != null)
		{
			request.FilterExpression = $"Education = :v_Education";
			request.ExpressionAttributeValues = new()
			{
				{ ":v_Education", new AttributeValue { S = education } }
			};
		}
		return await _db.ScanAsyncPaginated(request, "Student", nr, amount,
			x => new Student(x["Name"].S, x["Email"].S, x["Education"].S, int.Parse(x["Semester"].N)
		));
	}

	public async Task<IEnumerable<Student>> GetStudents()
	{
		return await _db.QueryAllAsync<StudentDyn>("Student");
	}

	public async Task<Student?> StudentByEmail(string email)
	{
		return await _db.QueryAsync<StudentDyn, string>(email);
	}

	public async Task<bool> UpdateStudent(Student student)
	{
		StudentDyn studentDyn = new StudentDyn(student);
		return await _db.InsertOrUpdateAsync(studentDyn);
	}
}
