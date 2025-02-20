using DataLayer.EF.Context;
using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataLayer.Pallets
{
    public class EFPalletRepository : PalletRepository
    {
        private readonly IPalletContext _palletContext;

        public EFPalletRepository(IPalletContext palletContext)
        {
            _palletContext = palletContext;
        }   

        public bool AddPallet(Pallet pallet)
        {
            _palletContext.Pallets().Add(pallet);
            return _palletContext.Save()>0;       
        }

        public bool DeletePallet(Guid Id)
        {
            Pallet? findpallet = _palletContext.Pallets().FirstOrDefault(p => p.Id == Id);
            if (findpallet == null) return false;
            _palletContext.Pallets().Remove(findpallet);
            return _palletContext.Save() > 0;
        }

        public Pallet? GetPallet(Guid Id)
        {
            return _palletContext.Pallets().FirstOrDefault(p => p.Id == Id);
        }

        public IEnumerable<Pallet> GetPallets()
        {
            return _palletContext.Pallets().ToList();
        }

        public bool UpdatePallet(Pallet p)
        {
            // find existing pallet by id
            Pallet? existingPallet = _palletContext.Pallets().FirstOrDefault(pallet => pallet.Id == p.Id);

            if (existingPallet == null) return false;
            existingPallet.Dimensions = new Dimensions3D() { X = p.Dimensions.X, Z = p.Dimensions.Z, Y = p.Dimensions.Y };
            existingPallet.Description = p.Description;
            existingPallet.PalletGroup = p.PalletGroup;
            existingPallet.PalletMaterial = p.PalletMaterial;
            existingPallet.Weight = p.Weight;
            existingPallet.MaxHeight = p.MaxHeight;
            existingPallet.MaxWeight = p.MaxWeight;
            existingPallet.OverReach = p.OverReach;
            existingPallet.Spots = p.Spots;
            existingPallet.SpecialCargo = p.SpecialCargo;
            existingPallet.SpaceBetween = p.SpaceBetween;
            existingPallet.InUse = p.InUse;

            return _palletContext.Save() > 0;
        }
    }
}
