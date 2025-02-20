using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Linq;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Infrastructure.Api;

public class ApiAccess : IApiAccess
{
	private readonly JsonSerializerOptions _jsonOptions = new JsonSerializerOptions() { PropertyNameCaseInsensitive = true };
	private readonly HttpClient _client = new HttpClient() { Timeout = TimeSpan.FromSeconds(500) };
	public void Configure(IConfiguration configuration, string key)
	{
		_client.BaseAddress = new Uri(configuration["ApiUrls:" + key]!);
		_client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
	}

	public async Task<T?> PostData<T, U>(string url, U parameter, string token = "")
	{
		try
		{
			string data = JsonSerializer.Serialize(parameter);
			StringContent content = new StringContent(data, Encoding.UTF8, "application/json");
			_client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
			using HttpResponseMessage response = await _client.PostAsync(url, content);
			var a = await response.Content.ReadAsStringAsync();
			return JsonSerializer.Deserialize<T>(await response.Content.ReadAsStringAsync(), _jsonOptions);
		}
		catch (Exception ex)
		{
			return default;
		}
	}
	public async Task<T?> UpdateData<T, U>(string url, U parameter, string token = "")
	{
		string data = JsonSerializer.Serialize(parameter);
		StringContent content = new StringContent(data, Encoding.UTF8, "application/json");
		_client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
		using HttpResponseMessage response = await _client.PutAsync(url, content);
		return JsonSerializer.Deserialize<T>(await response.Content.ReadAsStringAsync(), _jsonOptions);
	}
	public async Task<T?> DeleteData<T>(string url, string token = "")
	{
		_client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
		using HttpResponseMessage response = await _client.DeleteAsync(url);
		return JsonSerializer.Deserialize<T>(await response.Content.ReadAsStringAsync(), _jsonOptions);
	}

	public async Task<IEnumerable<T>> QueryMultiple<T>(string url, string token = "")
	{
		_client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
		using (HttpResponseMessage response = await _client.GetAsync(url))
		{
			string data = await response.Content.ReadAsStringAsync();
			return JsonSerializer.Deserialize<IEnumerable<T>>(data, _jsonOptions) ?? Enumerable.Empty<T>();
		}
	}
	public async Task<T?> QuerySingle<T>(string url, string token = "")
	{
		_client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
		using (HttpResponseMessage response = await _client.GetAsync(url))
		{
			string data = await response.Content.ReadAsStringAsync();
			return data != string.Empty ?
			JsonSerializer.Deserialize<T>(data, _jsonOptions) :
			default;
		}
	}

}
