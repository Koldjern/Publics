using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Models.Entities;

namespace Models.Common;

public class CalculationRequest
{
    public PlacedOrder Order { get; set; }
    public bool GroupByBatch { get; set; }
	public IEnumerable<Pallet> Pallets { get; set; }

	public CalculationRequest(PlacedOrder order, Pallet[] pallets)
    {
        GroupByBatch = order.GroupByBatch;
        Order = order;
        Pallets = pallets;
    }
}
