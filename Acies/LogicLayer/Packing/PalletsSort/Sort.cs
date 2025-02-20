using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort;

public class Sort
{
    public Group? Group;
    public List<PalletAndElements> Pairs { get; set; } = new List<PalletAndElements>();
    public Sort()
    {
        
    }
    public Sort(IEnumerable<Pallet> pallets, Group? group)
    {
        Group = group;
        foreach (var pallet in pallets)
        {
            Pairs.Add(new PalletAndElements(pallet));
        }
    }
}
