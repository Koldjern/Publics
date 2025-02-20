using Microsoft.EntityFrameworkCore;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.EF.Context;

public class PalletContext : DbContext, IPalletContext
{

	public PalletContext(DbContextOptions<PalletContext> options) : base(options)
	{
	}
	public DbSet<Pallet> Pallets()
	{
		return Set<Pallet>();
	}

	public int Save()
	{
		return SaveChanges();
	}
	//mapping for class and sql
	protected override void OnModelCreating(ModelBuilder modelBuilder)
	{
		modelBuilder.Entity<Pallet>((builder) =>
		{
			builder.ToTable("Pallets");
			builder.Property(x => x.Description).HasColumnName("Description");
			builder.OwnsOne(p => p.Dimensions, owned =>
			{
				owned.Property(d => d.X).HasColumnName("Width");
				owned.Property(d => d.Y).HasColumnName("Height");
				owned.Property(d => d.Z).HasColumnName("Depth");
			});
			builder.Property(x => x.PalletGroup).HasColumnName("PalletGroup");
			builder.Property(x => x.PalletMaterial).HasColumnName("PalletMaterial");
			builder.Property(x => x.Weight).HasColumnName("Weight");
			builder.Property(x => x.MaxHeight).HasColumnName("MaxHeight");
			builder.Property(x => x.MaxWeight).HasColumnName("MaxWeight");
			builder.Property(x => x.OverReach).HasColumnName("OverReach");
			builder.Property(x => x.Spots).HasColumnName("Spots");
			builder.Property(x => x.SpecialCargo).HasColumnName("SpecialCargo");
			builder.Property(x => x.SpaceBetween).HasColumnName("SpaceBetween");
			builder.Property(x => x.InUse).HasColumnName("InUse");
			builder.HasKey(x => x.Id);
			//The Id is generated in the application
			builder.Property(x => x.Id).ValueGeneratedNever();
		});
	}
}
