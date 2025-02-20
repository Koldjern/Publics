using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace DataLayer.Files.TypeReaders.Links;

public class XmlHandler : ITypeHandler
{
	public ITypeHandler? Next { get; set; }

	public T? Read<T>(string content)
	{
        XmlRootAttribute xmlRoot = new();
        xmlRoot.ElementName = "ArrayOrder";
        try
		{
			XmlSerializer serializer = new XmlSerializer(typeof(T), xmlRoot);

			// Create a StringReader to read the XML string
			using (StringReader reader = new StringReader(content))
			{
				// Deserialize the XML string and cast to T
				return (T)serializer.Deserialize(reader)!;  
			}
		}
		catch (InvalidOperationException)
		{
			return Next != null ? Next.Read<T>(content) : default;
		}
	}
}
