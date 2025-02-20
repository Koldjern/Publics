using LogicLayer.Packing.PalletsSort;
using LogicLayer.Packing.PlacementOptimization;
using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.Placement.Decorators;

public class PlacementQuantity : IPlacement
{
	public CalculationResult Placement(SortResult request)
	{
		request.Result.ForEach(x => x.Pairs.ForEach(pair => Expand(pair)));
		return new CalculationResult(request.Request);
	}
	private void Expand(PalletAndElements pair)
	{
        for (int i = 0; i < pair.Elements.Count; i++)
			if (pair.Elements[i].Quantity > 1)
				for (int j = 0; j < pair.Elements[i].Quantity; j++)
				{
					pair.Elements.Insert(i+1, new PlacedElement(pair.Elements[i]) { Quantity = 1});
					if (j == pair.Elements[i].Quantity - 1)
					{
						pair.Elements[i].Quantity = 1;
						i = i + j+1;
					}
				}

    }
}
