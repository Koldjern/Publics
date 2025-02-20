namespace EX_STA;

public static class Exstensions
{
    public static void ForEach<T>(this T[] values, Action<T> action)
    {
        for (int i = 0; i < values.Length; i++)
            action(values[i]);
    }
    public static void ForEach<T>(this T[] values, Action<T, int> action)
    {
        for (int i = 0; i < values.Length; i++)
            action(values[i], i);
    }
    public static void ForEach<T>(this T[] values, Action<T[], int> action)
    {
        for (int i = 0; i < values.Length; i++)
            action(values, i);
    }
    public static void TryCatchLoop<T>(this T t, Func<bool> condition, Func<T> func)
    {
        while (condition())
        {
            try
            {
                t = func();
            }
            catch (Exception e)
            {
                throw new Exception("WRONG Input");
            }
        }
    }
    public static T TryCatchLoop<T>(this T t, Func<T> func)
    {
        while (true)
        {
            try
            {
                return func();
            }
            catch (Exception e)
            {
                throw new Exception("WRONG Input");
            }
        }
    }
    public static bool NotNull<T>(this T? t)
    {
        return t != null;
    }
    public static void NotNull<T>(this T? t, Action<T> action)
    {
        if (t != null)
            action(t);
    }
    public static void NotNull<T>(this T? t, Action<T> action, Action actionnNull)
    {
        if (t != null)
            action(t);
        else
            actionnNull();
    }
    public static T IfNull<T>(this object? t, Func<T> func)
    {
        try
        {
            if (t != null)
                return (T)t;
            else
                return func();
        }
        catch (Exception e)
        {
            return func();
        }
    }
}
