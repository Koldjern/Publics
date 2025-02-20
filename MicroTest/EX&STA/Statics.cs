using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EX_STA;

public static class Statics
{
    public static void WhileLoop(Func<bool> condition, params Action[] actions)
    {
        while (condition())
            actions.ForEach((x) => x());
    }

    /// <summary>
    /// Remember to use a Reference value in the condition
    /// </summary>
    public static void WhileLoop<U>(Func<U, bool> condition, U u, params Action<U>[] actions)
    {
        while (condition(u))
            actions.ForEach((x) => x(u));
    }
    public static void ForUntil(int start, int to, Action action)
    {
        for (int i = start; i < to; i++)
            action();
    }
    public static void ForUntil(int start, int to, Action<int> action)
    {
        for (int i = start; i < to; i++)
            action(i);
    }
}
