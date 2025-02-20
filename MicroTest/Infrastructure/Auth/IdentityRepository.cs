using Application;
using Domain.DTO.Send;
using Infrastructure.Api;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace Infrastructure.Auth;

public class IdentityRepository : IIdentityRepository
{
	private readonly IApiAccess _apiAccess;
	private readonly IConfiguration _configuration;
	public string token;
	public IdentityRepository(IApiAccess apiAccess, IConfiguration configuration)
	{
		_configuration = configuration;
		_apiAccess = apiAccess;
		_apiAccess.Configure(configuration, "Auth");
	}
	public async Task<string?> Login(Login model)
	{
		return await _apiAccess.PostData<string, Login>("Login", model);
	}

	public async Task<bool> Register(Register model)
	{
		return await _apiAccess.PostData<bool, Register>("Register", model, token);
	}
}
