using LogicLayer.Auth;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Models.Entities;
using PresentationLayer.Models;
using System.Runtime.InteropServices;

namespace PresentationLayer.Controllers;

public class UserController : Controller
{
    
    private IAuthService _authservice; 

    public UserController(IAuthService authService)  
    {
        _authservice = authService;
       
    }

    public IActionResult Login(string url)
    {
        Login model = new() { Url = url };
        return View(model);
    }

    [HttpPost]
    public IActionResult Login(Login model)
    {

        bool login = _authservice.Login(model.Username, model.Password);

        if(login) 
        {
				return RedirectToAction("Index","Home");
				//return RedirectToAction("List", "Order");
        }
        return View();
    }

    public async Task<RedirectResult> Logout(string url = "/")
    {
        _authservice.Logout();
        return Redirect(url);
    }
}
