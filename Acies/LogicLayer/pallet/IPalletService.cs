using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.pallet
{
    public interface IPalletService
    {
        Pallet? GetPallet(Guid Id);
        IEnumerable<Pallet> GetPallets();
        bool UpdatePallet(Pallet p);
        bool DeletePallet(Guid Id);
        bool AddPallet(Pallet pallet);
    }
}
