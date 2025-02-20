using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Files.TypeReaders;

public static class HandlerExstensions
{
	public static ITypeHandler Add(this ITypeHandler main, ITypeHandler other)
	{
		main.Next = other;
		return main;
	}
}
