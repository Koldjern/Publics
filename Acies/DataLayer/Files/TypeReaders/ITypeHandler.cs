using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Files.TypeReaders;

public interface ITypeHandler
{
	ITypeHandler? Next { get; set; }
	T? Read<T>(string content); 

}
