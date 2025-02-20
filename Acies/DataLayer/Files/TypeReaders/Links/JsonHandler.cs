using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace DataLayer.Files.TypeReaders.Links;

public class JsonHandler : ITypeHandler
{
	public ITypeHandler? Next { get; set; }

	public T? Read<T>(string content)
	{
		try
		{
			// Attempt to deserialize the string to an object (using dynamic type here)
			return JsonSerializer.Deserialize<T>(content)!;
		}
		catch (JsonException)
		{
			return Next != null ? Next.Read<T>(content) : default;
		}
	}
}
