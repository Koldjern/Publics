using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class PalletAndElements : Pallet
{
	public virtual List<PlacedElement> Elements { get; set; } = new List<PlacedElement>();
    public PalletAndElements()
    {
        
    }
    public PalletAndElements(Pallet other) : base(other)
    {
        
    }
}
