using Front.Models.Common;
using System.Text.Json;

namespace Front.Common;

public static class Extensions
{
	public static string PathAndQuery(this HttpRequest request) =>
	request.QueryString.HasValue
	? $"{request.Path}{request.QueryString}"
	: request.Path.ToString();
	public static void SetJson<T>(this ISession session, string key, T value)
	{
		session.SetString(key, JsonSerializer.Serialize<T>(value));
	}
	public static T? GetJson<T>(this ISession session, string key)
	{
		var sessionData = session.GetString(key);
		return sessionData == null
		? default : JsonSerializer.Deserialize<T>(sessionData);
	}
	public static T? GetJson<T>(this ISession session)
	{
		var sessionData = session.GetString(typeof(T).Name);
		return sessionData == null
		? default : JsonSerializer.Deserialize<T>(sessionData);
	}
}
