using Amazon.DynamoDBv2.DataModel;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraStudent.Persistence.Dynamo.Mapping;
[DynamoDBTable("TestStudents")]
public class StudentDyn : Student
{
    [DynamoDBProperty]
    public virtual string Name { get; set; } = "";
    [DynamoDBHashKey]
    public virtual string Email { get; set; } = "";
    [DynamoDBProperty]
    public virtual string Education { get; set; } = "";
    [DynamoDBProperty]
    public virtual int Semester { get; set; }
	[DynamoDBProperty]
	public string Entity { get; set; }
	public StudentDyn()
    {

    }
    public StudentDyn(Student other)
    {
        Name = other.Name;
        Email = other.Email;
        Education = other.Education;
        Semester = other.Semester;
    }
}
