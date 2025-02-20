using DataLayer.EF.Context;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Auth;

public class AuthService : IAuthService
{
	private readonly UserManager<IdentityUser> _userManager;
	private readonly SignInManager<IdentityUser> _signInManager;
    public AuthService(UserManager<IdentityUser> userManager, SignInManager<IdentityUser> signInManager)
    {
        _userManager = userManager;
        _signInManager = signInManager;
    }

    public bool Login(string name, string password)
    { 
        IdentityUser? signin = Task.Run(() =>_userManager.FindByNameAsync(name)).Result;
        if (signin == null) return false;
            _signInManager.SignOutAsync();
        if (Task.Run(() => _signInManager.PasswordSignInAsync(signin, password, false, false)).Result.Succeeded)
            return true;
        return false;
    }

    public void Logout()
    {
        _signInManager.SignOutAsync();
    }
}
