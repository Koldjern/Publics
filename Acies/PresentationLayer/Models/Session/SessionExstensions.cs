using System.Text.Json;

namespace PresentationLayer.Models.Session;

public static class SessionExstensions
{
    public static void SetJson(this ISession session, string key, object value)
    {
        session.SetString(key, JsonSerializer.Serialize(value));
    }
    public static T? GetJson<T>(this ISession session, string key)
    {
        var sessionData = session.GetString(key);
        return sessionData == null
        ? default : JsonSerializer.Deserialize<T>(sessionData);
    }
}
