using DataLayer.Pallets;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.pallet
{
    public class PalletService : IPalletService
    {
        private readonly DataLayer.Pallets.PalletRepository _palletRepository;

        public PalletService(DataLayer.Pallets.PalletRepository palletRepository)
        {
            _palletRepository = palletRepository;
        }
        public bool AddPallet(Pallet pallet)
        {
            return _palletRepository.AddPallet(pallet);
        }

        public bool DeletePallet(Guid Id)
        {
            if (Id == Guid.Empty) return false;
            return _palletRepository.DeletePallet(Id);

        }

        public Pallet? GetPallet(Guid Id)
        {
            if (Id == Guid.Empty) return null;
           return _palletRepository.GetPallet(Id);
            
        }

        public IEnumerable<Pallet> GetPallets()
        {
            return _palletRepository.GetPallets();
        }

        public bool UpdatePallet(Pallet p)
        {
            return _palletRepository.UpdatePallet(p);
        }
    }
}
