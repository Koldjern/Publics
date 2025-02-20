using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppStudent.Persistence.Repository;

public interface IStudentRepository
{
	Task<IEnumerable<string>> GetEducations();
    Task<IEnumerable<Student>> GetStudents();
	Task<IEnumerable<Student>> GetPagedAsync(int nr, int amount, string? education);
	Task<Student?> StudentByEmail(string email);
    Task<bool> AddStudent(Student student);
    Task<bool> UpdateStudent(Student student);
    Task<bool> DeleteStudent(string email);
	Task<int> GetMaxAsync(string? education);
}
