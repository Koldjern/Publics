using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Api;

public interface IApiAccess
{
	public void Configure(IConfiguration configuration, string key);
	Task<IEnumerable<T>> QueryMultiple<T>(string url, string token = "");
	Task<T?> QuerySingle<T>(string url, string token = "");
	Task<T?> PostData<T, U>(string url, U parameter, string token = "");
	Task<T?> UpdateData<T, U>(string url, U parameter, string token = "");
	Task<T?> DeleteData<T>(string url, string token = "");

}
