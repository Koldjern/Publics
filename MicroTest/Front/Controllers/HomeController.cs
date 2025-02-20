using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Domain.Entities;
using Front.Models.Common;
using Front.Models.Home;
using Front.Models.Modals.Student;
using Front.Models.Products;
using Front.Models.Students;
using Microsoft.AspNetCore.Mvc;
using EX_STA;
using Application;
using Domain.DTO.Send;
using Microsoft.AspNetCore.Authorization;
using Infrastructure.Auth;
using InfraFront;

namespace Front.Controllers;
public class HomeController : Controller
{
	public int PageSize = 4;
	private readonly IProductRepository _productRepository;
	private readonly IStudentRepository _studentRepository;
	private readonly IIdentityRepository _identityRepository;


	public HomeController(IProductRepository productRepository, IStudentRepository studentRepository, IIdentityRepository identityRepository)
	{
		_identityRepository = identityRepository;
		_productRepository = productRepository;
		_studentRepository = studentRepository;
	}
	public async Task<IActionResult> Index(string? category, string? education, int productPage = 1, int studentPage = 1)
	{
		var a = await _identityRepository.Register(new Register { Username = "Bdmin", Password = "$Secret123" });
		var b = await _identityRepository.Login(new Login { Username = "Admin", Password = "Secret123$" });
		var c = await _productRepository.GetPagedAsync(productPage, PageSize, category);
		((IdentityRepository)_identityRepository).token = b;
		((ProductRepository)_productRepository).Token = b;
		a = await _identityRepository.Register(new Register { Username = "Bdmin", Password = "$Secret123" });
		c = await _productRepository.GetPagedAsync(productPage, PageSize, category);
		var cookieOptions = new CookieOptions
		{
			HttpOnly = true,  // Prevents JavaScript access
			Secure = true,    // Only send over HTTPS
			SameSite = SameSiteMode.Strict, // Prevents CSRF
			Expires = DateTime.UtcNow.AddHours(1) // Set to token expiry
		};

		// Store token in a secure cookie
		Response.Cookies.Append("AuthToken", b, cookieOptions);

		ViewBag.SelectedCategory = category;
		ViewBag.SelectedEducation = education;
		ViewBag.StudentPage = studentPage;
		ViewBag.ProductPage = productPage;
		var products = await _productRepository.GetPagedAsync(productPage, PageSize, category);
		int maxProducts = await _productRepository.GetMaxAsync(category);
		var students = await _studentRepository.GetPagedAsync(studentPage, PageSize, education);
		int maxStudents = await _studentRepository.GetMaxAsync(education);
		return View("Index", new IndexViewModel()
		{
			Products = new ProductsViewModel(products, new PagingInfo(productPage, PageSize, maxProducts), category),
			Students = new StudentsViewModel(students, new PagingInfo(studentPage, PageSize ,maxStudents), education)
		});
	}
}
