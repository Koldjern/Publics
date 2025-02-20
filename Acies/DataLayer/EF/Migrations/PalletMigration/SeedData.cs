using DataLayer.EF.Context;
using Microsoft.AspNetCore.Builder;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.EF.Migrations.PalletMigration;

public static class SeedData
{
	public static void AddDbAndPallets(this IServiceCollection services, bool dev)
	{
		PalletContext context = services.BuildServiceProvider().CreateScope().ServiceProvider.GetRequiredService<PalletContext>();
		if (context.Database.GetPendingMigrations().Any())
		{
			context.Database.Migrate();
		}
		if (dev && !context.Pallets().Any())
		{
			context.Pallets().AddRange(
			new Pallet()
			{
				Id = Guid.NewGuid(),
				Description = "Europalle standard mål",
				Dimensions = new Dimensions3D { X = 1200, Y = 150, Z = 750 }, //standard europalle mål
				PalletGroup = "tung",
				PalletMaterial = "Træ",
				Weight = 25,
				MaxHeight = 3000,
				MaxWeight = 1500,
				OverReach = 500,
				Spots = 5,
				SpecialCargo = false,
				SpaceBetween = 3,
				InUse = true,
			},

			new Pallet()
			{
				Id = Guid.NewGuid(),
				Description = "palle til shipping",
				Dimensions = new Dimensions3D { X = 1500, Y = 200, Z = 850 }, //standard europalle mål
				PalletGroup = "tung",
				PalletMaterial = "Træ",
				Weight = 22,
				MaxHeight = 3000,
				MaxWeight = 1300,
				OverReach = 500,
				Spots = 4,
				SpecialCargo = false,
				SpaceBetween = 3,
				InUse = true,
			},
			new Pallet()
			{
				Id = Guid.NewGuid(),
				Description = "palle til shipping",
				Dimensions = new Dimensions3D { X = 2000, Y = 100, Z = 700 }, //standard europalle mål
				PalletGroup = "tung",
				PalletMaterial = "Metal",
				Weight = 22,
				MaxHeight = 3000,
				MaxWeight = 1300,
				OverReach = 500,
				Spots = 4,
				SpecialCargo = false,
				SpaceBetween = 3,
				InUse = true,
			}
			);
			context.SaveChanges();
		}
	}
}
