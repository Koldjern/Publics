using AppStudent.Persistence.Repository;
using Domain.Entities;

namespace StudentAPI;

public static class Api
{
    public static void ConfigureApi(this WebApplication app)
    {
		app.MapGet("Students/Nr/{nr}/Amount/{amount}/Education/{education?}", GetPagedAsync);
		app.MapGet("Students", GetAllAsync);
        app.MapPost("Students", PostAsync);
        app.MapDelete("Students/Email/{email}", DeleteAsync);
        app.MapGet("Students/Email/{email}", GetByMailAsync);
        app.MapPut("Students", UpdateAsync);
        app.MapGet("Students/Max/Education/{education?}", GetMax);
		app.MapGet("Students/Educations", GetEducations);
	}

	private async static Task<IResult> GetEducations(IStudentRepository studentRepository)
	{
		return await Act(studentRepository.GetEducations(), (x) => x != null);
	}
	private async static Task<IResult> GetPagedAsync(int nr, int amount, string? education, IStudentRepository studentRepository)
	{
		return await Act(studentRepository.GetPagedAsync(nr, amount, education), (x) => x != null);
	}
	private async static Task<IResult> GetMax(string? education, IStudentRepository studentRepository)
	{
		return await Act(studentRepository.GetMaxAsync(education), (x) => true);
	}
	private async static Task<IResult> UpdateAsync(Student student, IStudentRepository studentRepository)
    {
        return await Act(studentRepository.UpdateStudent(student), x => x);
    }
    private async static Task<IResult> GetByMailAsync(string email, IStudentRepository studentRepository)
    {
        return await Act(studentRepository.StudentByEmail(email), x => x != null);
    }
    private async static Task<IResult> DeleteAsync(string email, IStudentRepository studentRepository)
    {
        return await Act(studentRepository.DeleteStudent(email), x => x);
    }
    private async static Task<IResult> PostAsync(Student student, IStudentRepository studentRepository)
    {
        return await Act(studentRepository.AddStudent(student), x => x);
    }
    private async static Task<IResult> GetAllAsync(IStudentRepository studentRepository)
    {
        return await Act(studentRepository.GetStudents(), x => x != null);
    }
    private static async Task<IResult> Act<T>(Task<T> getter, Func<T, bool> condition)
    {
        try
        {
            var results = await getter;
            return condition(results) ? Results.Ok(results) : Results.NotFound(false);
        }
        catch (Exception ex)
        {
            return Results.Problem(ex.Message);
        }
    }
}
