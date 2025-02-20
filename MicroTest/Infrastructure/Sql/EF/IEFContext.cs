using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Infrastructure.Sql.EF;

public interface IEFContext
{
	Task<int> SaveDB();

}
