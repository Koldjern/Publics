using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Sql.Dapper;

public interface ISqlAccess
{
    Task<IEnumerable<T>>? LoadData<T, U>(string storedProcedure, U parameters, string connectionKey);
    Task<int> SaveData<T>(string storedProcedure, T parameters, string connectionKey);
}
