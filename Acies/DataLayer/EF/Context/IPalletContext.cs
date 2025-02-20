using Microsoft.EntityFrameworkCore;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.EF.Context;

public interface IPalletContext 
{
	DbSet<Pallet> Pallets();
	int Save();
}
