using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Domain.Entities;
using Front.Models.Modals.Student;
using Front.Models.Students;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.AspNetCore.Mvc.ViewEngines;
using Microsoft.AspNetCore.Mvc.ViewFeatures;

namespace Front.Controllers;

public class StudentModalController : Controller
{
	private readonly IStudentRepository _studentRepository;

	public StudentModalController(IStudentRepository studentRepository)
	{
		_studentRepository = studentRepository;
	}
	public async Task<IActionResult> CreateStudentModal()
	{
		StudentModal model = new StudentModal();
		PartialViewResult result = PartialView("StudentModal", model);
		return result;
	}
	public async Task<IActionResult> UpdateStudentModal(string email)
	{
		StudentModal model = new StudentModal();
		model.TaskDescription = "Update";
		model.ActionString = "UpdateStudent";
		Student student = await _studentRepository.StudentByEmail(email) ?? new Student();
		model.Student = new StudentViewModel(student);
		PartialViewResult result = PartialView("StudentModal", model);
		return result;
	}
	[HttpPost]
	public async Task<IActionResult> CreateStudent(Student student)
	{
		if (!ModelState.IsValid)
		{
			StudentModal model = new StudentModal();
			model.Validate = true;
			model.Student = new StudentViewModel(student);
			return Json(new
			{
				success = false
			});
		}
		var success = await _studentRepository.AddStudent(student);
		//if something
		int productPage = ViewBag.ProductPage != null ? (int)ViewBag.ProductPage : 1;
		int studentPage = ViewBag.StudentPage != null ? (int)ViewBag.StudentPage : 1;
		string? education = ViewBag.SelectedEducationas as string;
		string? category = ViewBag.SelectedCategory as string;

		return Json(new
		{
			success = true,
			redirectUrl = Url.Action("Index", "Home", new { category, education, productPage, studentPage })
		});
	}
	[HttpPost]
	public async Task<IActionResult> UpdateStudent(Student student)
	{
		if (!ModelState.IsValid)
		{
			StudentModal model = new StudentModal();
			model.Validate = true;
			model.Student = new StudentViewModel(student);
			return Json(new
			{
				success = false,
			});
		}
		var success = await _studentRepository.UpdateStudent(student);
		//if something
		int productPage = ViewBag.ProductPage != null ? (int)ViewBag.ProductPage : 1;
		int studentPage = ViewBag.StudentPage != null ? (int)ViewBag.StudentPage : 1;
		string? education = ViewBag.SelectedEducationas as string;
		string? category = ViewBag.SelectedCategory as string;

		return Json(new
		{
			success = true,
			redirectUrl = Url.Action("Index", "Home", new { category, education, productPage, studentPage })
		});
	}
}
