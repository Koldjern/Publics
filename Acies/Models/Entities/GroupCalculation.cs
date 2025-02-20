using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class GroupCalculation
{
	public Group? Group { get; set; }
	public List<PackedPallet> PackedPallets { get; set; } = new List<PackedPallet>();
}
