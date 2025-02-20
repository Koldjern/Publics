using DataLayer.EF.Context;
using DataLayer.Pallets;
using Microsoft.EntityFrameworkCore;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tests.Data.PalletTest
{
    public class EFPalletRepositoryTestsInMemory
    {
        [Fact]
        public void AddPalletInMemoryTest()
        {

            //arrange
            var options = new DbContextOptionsBuilder<PalletContext>().UseInMemoryDatabase("testdatabase").Options;
            var id = Guid.NewGuid();
            var pallet = new Pallet()
            {
                Id = id,
                Description = "testpallet"
            };
            
            //act
            using (PalletContext pallecontext = new PalletContext(options))
            {
                var EFRepo = new EFPalletRepository(pallecontext);
                bool Add = EFRepo.AddPallet(pallet);
                Pallet? found = pallecontext.Pallets().FirstOrDefault(X => X.Id == id);
                //assert
                Assert.True(Add);
                Assert.Equal(pallet, found);
            }

        }
        [Fact]
        public void DeletePalletInMemoryTest()
        {
            //arrange
            var options = new DbContextOptionsBuilder<PalletContext>().UseInMemoryDatabase("testdatabase").Options;
            var id = Guid.NewGuid();
            var pallet = new Pallet()
            {
                Id = id,
                Description = "testpallet"
            };
            //act
            using (PalletContext pallecontext = new PalletContext(options))
            {
                var EFRepo = new EFPalletRepository(pallecontext);
                // EFRepo.addpallet has to be function for this to work
                EFRepo.AddPallet(pallet);
                bool Delete = EFRepo.DeletePallet(id);
                Pallet? found = pallecontext.Pallets().FirstOrDefault(X => X.Id == id);


                //assert
                Assert.True(Delete);
                Assert.Null(found);
            }

        }
        [Fact]
        public void UpdatePalletInMemoryTest()
        {

            //arrange
            var options = new DbContextOptionsBuilder<PalletContext>().UseInMemoryDatabase("testdatabase").Options;
            var id = Guid.NewGuid();
            var pallet = new Pallet()
            {
                Id = id,
                Description = "testpallet"
            };

            //act
            using (PalletContext pallecontext = new PalletContext(options))
            {
                var EFRepo = new EFPalletRepository(pallecontext);
                EFRepo.AddPallet(pallet);
                pallet.Description = "Updated Description";
                bool update = EFRepo.UpdatePallet(pallet);
                Pallet? found = pallecontext.Pallets().FirstOrDefault(X => X.Id == id);
                //assert
                Assert.True(update);
                Assert.Equal("Updated Description", found!.Description);
            }

        }
        [Fact]
        public void GetPalletInMemoryTest()
        {
            //arrange
            var options = new DbContextOptionsBuilder<PalletContext>().UseInMemoryDatabase("testdatabase").Options;
            var id = Guid.NewGuid();
            var pallet = new Pallet()
            {
                Id = id,
                Description = "testpallet"
            };
            //act
            using (PalletContext pallecontext = new PalletContext(options))
            {
                var EFRepo = new EFPalletRepository(pallecontext);
                // EFRepo.addpallet has to be function for this to work
                EFRepo.AddPallet(pallet);
                Pallet? found = EFRepo.GetPallet(id);


                //assert
              
                Assert.NotNull(found);
            }

        }
    }
}
