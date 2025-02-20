using DataLayer.Files.TypeReaders.Links;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Files.TypeReaders;

public class DataTranslater : ITypeHandler
{ 
	public ITypeHandler? Next { get; set; }

    public DataTranslater()
    {
        Next = new JsonHandler().Add(new XmlHandler());
    }

	public T? Read<T>(string content)
	{
		return Next!.Read<T>(content);
	}
}
