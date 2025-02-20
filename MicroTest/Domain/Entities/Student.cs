using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Entities;

public class Student
{
	public virtual string Name { get; set; } = "";
	public virtual string Email { get; set; } = "";
	public virtual string Education { get; set; } = "";
	public virtual int Semester { get; set; }
    public Student()
    {

    }
	public Student(string name, string email, string education, int semester)
	{
        Name = name;
        Email = email;
        Education = education;
        Semester = semester;
	}
	public Student(Student other)
    {
        Name = other.Name;
        Email = other.Email;
        Education = other.Education;
        Semester = other.Semester;
    }
}
