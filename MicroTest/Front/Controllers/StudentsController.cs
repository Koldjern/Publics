using AppStudent.Persistence.Repository;
using Domain.Entities;
using Front.Models.Modals.Student;
using Front.Models.Students;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Front.Controllers;
[Authorize]
public class StudentsController : Controller
{
	private readonly IStudentRepository _studentRepository;
	public StudentsController(IStudentRepository studentRepository)
	{
		_studentRepository = studentRepository;
	}
	[HttpPost]
	public async Task<IActionResult> DeleteStudent(string email)
	{
		var success = await _studentRepository.DeleteStudent(email);

		int productPage = ViewBag.ProductPage != null ? (int)ViewBag.ProductPage : 1;
		int studentPage = ViewBag.StudentPage != null ? (int)ViewBag.StudentPage : 1;
		string? education = ViewBag.SelectedEducationas as string;
		string? category = ViewBag.SelectedCategory as string;
		return RedirectToAction("Index", "Home", new { category, education, productPage, studentPage });
	}
}
