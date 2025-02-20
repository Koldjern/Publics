using AppStudent.Persistence.Repository;
using Domain.Entities;
using Infrastructure.Sql.Dapper;
using InfraStudent.Persistence.EF;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraStudent.Persistence.Repository;

public class StudentRepository : IStudentRepository
{
    private readonly IStudentEFContext _context;
    public StudentRepository(IStudentEFContext context)
    {
        _context = context;
    }
    public async Task<bool> AddStudent(Student student)
    {
        if (await _context.Students.FindAsync(student.Email) != null)
            return false;
        _context.Students.Add(student);
        var result = await _context.SaveDB();
        return result > 0;
    }

    public async Task<bool> DeleteStudent(string email)
    {
        if (await _context.Students.FindAsync(email) is not Student student)
            return false;
        _context.Students.Remove(student);
        var result = await _context.SaveDB();
        if (result > 0)
            return true;
        return false;
    }

    public async Task<Student?> StudentByEmail(string email)
    {
        return await _context.Students.FindAsync(email);
    }

    public async Task<IEnumerable<Student>> GetStudents()
    {
        return await _context.Students.ToArrayAsync();
    }
    public async Task<bool> UpdateStudent(Student student)
    {
        if (await _context.Students.FindAsync(student.Email) is not Student update)
            return false;

        update.Name = student.Name;
        update.Email = student.Email;
        update.Education = student.Education;
        update.Semester = student.Semester;

        return await _context.SaveDB() > 0;
    }

	public async Task<IEnumerable<string>> GetEducations()
	{
		return await _context.Students
			.Select(x => x.Education)
			.Distinct()
			.OrderBy(x => x)
			.ToArrayAsync();
	}

	public async Task<IEnumerable<Student>> GetPagedAsync(int nr, int amount, string? education)
	{
		return await _context.Students
	        .Where(p => p.Education == education || education == null)
	        .Skip((nr - 1) * amount)
	        .Take(amount)
	        .ToArrayAsync();
	}

	public async Task<int> GetMaxAsync(string? education)
	{
		return await _context.Students
			.Where(p => p.Education == education || education == null)
			.CountAsync();
	}
}
