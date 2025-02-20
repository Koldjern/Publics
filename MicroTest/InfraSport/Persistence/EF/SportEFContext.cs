using Domain.Entities;
using Infrastructure.Sql.EF;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory;

namespace InfraSport.Persistence.EF;

public class SportEFContext : DbContext, ISportEFContext
{
	//dotnet ef migrations add Orders --project .\InfraSport\ --startup-project .\SportStoreAPI\
	public SportEFContext(DbContextOptions<SportEFContext> options) : base(options)
    {
    }
    public DbSet<Product> Products => Set<Product>();

	public DbSet<Order> Orders => Set<Order>();

	public async Task<int> SaveDB()
	{
		return await SaveChangesAsync();
	}
	protected override void OnModelCreating(ModelBuilder modelBuilder)
	{
		modelBuilder.Entity<Product>((pro) =>
		{
			pro.ToTable("Product");
			pro.Property(x => x.ProductID).HasColumnName("ProductID");
			pro.Property(x => x.Name).HasColumnName("Name");
			pro.Property(x => x.Description).HasColumnName("Description");
			pro.Property(x => x.Category).HasColumnName("Category");
			pro.Property(x => x.Price).HasColumnName("Price").HasColumnType("Decimal(8,2)");
			pro.HasKey(x => x.ProductID);
		});
		//modelBuilder.Entity<Order>((ord) =>
		//{
		//	ord.ToTable("Order");
		//	ord.Property(x => x.OrderID).HasColumnName("OrderID");
		//	ord.Property(x => x.Name).HasColumnName("Name");
		//	ord.Property(x => x.Line1).HasColumnName("Line1");
		//	ord.Property(x => x.Line2).HasColumnName("Line2");
		//	ord.Property(x => x.Line3).HasColumnName("Line3");
		//	ord.HasMany(x => x.Lines)
		//	.WithOne(x => x.)
		//	.HasForeignKey(x => x.OrderID)
		//	ord.HasKey(x => x.OrderID);
		//});
		//modelBuilder.Entity<CartLine>((cl) =>
		//{
		//	cl.ToTable("CartLine");
		//	cl.Property(x => x.CartLineID).HasColumnName("CartLineID");
		//	cl.Property(x => x.Product).HasColumnName("Product");
		//	cl.Property(x => x.Quantity).HasColumnName("Quantity");
		//	cl.HasKey(x => x.CartLineID);
		//});

	}

}
