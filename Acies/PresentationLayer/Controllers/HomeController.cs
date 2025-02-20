using DataLayer.Files.Settings;
using LogicLayer.Settings;
using Microsoft.AspNetCore.Mvc;
using Models.Entities.Settings;
using PresentationLayer.Models;
using System.Diagnostics;

namespace PresentationLayer.Controllers;

public class HomeController : Controller
{
    public HomeController()
    {

    }
    public ViewResult Index()
    {
        if (!User.Identity.IsAuthenticated)
        {
            return View("~/Views/User/Login.cshtml");
        }
        else
        { 
            return View();
        }
    }

	public IActionResult Error(string message)
	{
		return View(new ErrorViewModel { Message = message });
	}

}
