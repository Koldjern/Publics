using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Sql.Dapper;

public class SqlAccess : ISqlAccess
{

    Task<IEnumerable<T>>? ISqlAccess.LoadData<T, U>(string storedProcedure, U parameters, string connectionKey)
    {
        throw new NotImplementedException();
    }

    Task<int> ISqlAccess.SaveData<T>(string storedProcedure, T parameters, string connectionKey)
    {
        throw new NotImplementedException();
    }
}
