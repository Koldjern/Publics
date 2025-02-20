using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Auth;

public interface IAuthService
{
    public bool Login(string name, string password);
    public void Logout();

}
