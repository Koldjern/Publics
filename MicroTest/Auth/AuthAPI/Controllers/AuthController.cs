using Domain.DTO.Send;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;

namespace AuthAPI.Controllers;
[ApiController]
public class AuthController : ControllerBase
{
	private readonly UserManager<IdentityUser> _userManager;
	private readonly IConfiguration _configuration;

	public AuthController(UserManager<IdentityUser> userManager, IConfiguration configuration)
	{
		_userManager = userManager;
		_configuration = configuration;
	}
	[Authorize]
	[HttpPost("Register")]
	public async Task<IActionResult> Register([FromBody] Register model)
	{
		var user = new IdentityUser { UserName = model.Username, Email = model.Email };
		var result = await _userManager.CreateAsync(user, model.Password);

		if (!result.Succeeded)
			return BadRequest(result.Errors);

		return Ok("User registered successfully.");
	}

	[HttpPost("Login")]
	public async Task<IActionResult> Login([FromBody] Login model)
	{
		var user = await _userManager.FindByNameAsync(model.Username);
		if (user != null && await _userManager.CheckPasswordAsync(user, model.Password))
		{
			var token = GenerateJwtToken(user);
			return Ok(token);
		}
		return Unauthorized();
	}

	private string GenerateJwtToken(IdentityUser user)
	{
		try
		{

			var jwtSettings = _configuration.GetSection("Jwt");
			var rsa = RSA.Create();
			rsa.ImportRSAPrivateKey(Convert.FromBase64String(jwtSettings["PrivateKey"]), out _);

			//var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(jwtSettings["Key"]));
			//var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);
			var credentials = new SigningCredentials(new RsaSecurityKey(rsa), SecurityAlgorithms.RsaSha256);

			var claims = new[]
			{
			new Claim(JwtRegisteredClaimNames.Sub, user.UserName),
			new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
			new Claim(ClaimTypes.NameIdentifier, user.Id)
		};

			var token = new JwtSecurityToken(
				issuer: jwtSettings["Issuer"],
				audience: jwtSettings["Audience"],
				claims: claims,
				expires: DateTime.Now.AddMinutes(double.Parse(jwtSettings["ExpiresInMinutes"])),
				signingCredentials: credentials
			);
			return new JwtSecurityTokenHandler().WriteToken(token);
		}
		catch (Exception ex)
		{
			return "";
		}
	}
}
