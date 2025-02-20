using Domain.DTO.Send;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application;

public interface IIdentityRepository
{
	Task<bool> Register(Register model);
	Task<string?> Login(Login model);
}
