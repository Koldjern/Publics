using Domain.Entities;
using Infrastructure.Sql.EF;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.EF;

public interface ISportEFContext : IEFContext
{
	public DbSet<Product> Products { get; }
	public DbSet<Order> Orders { get; }

}
