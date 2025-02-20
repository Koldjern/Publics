using Domain.Entities;
using System.ComponentModel.DataAnnotations;

namespace Front.Models.Students;

public class StudentViewModel : Student
{
    [StringLength(30, MinimumLength = 2)]
    [Required(ErrorMessage = "Name Required, should be between 2 and 30")]
    public override string Name { get; set; } = "";
    [EmailAddress]
    [StringLength(30, MinimumLength = 2)]
    [Required(ErrorMessage = "Email Required, Email should be between 2 and 30")]
    public override string Email { get; set; } = "";
	[StringLength(30, MinimumLength = 2)]
	[Required(ErrorMessage = "Education Name Required")]
    public override string Education { get; set; } = "";
    [Range(1, 10)]
    [Required(ErrorMessage = "Semester stage Required, Minimum 1, Maximum 10")]
    public override int Semester { get; set; }
    public StudentViewModel() 
    {

    }
    public StudentViewModel(Student other) : base(other)
    {

    }
}
