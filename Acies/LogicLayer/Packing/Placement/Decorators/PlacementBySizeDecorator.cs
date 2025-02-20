using LogicLayer.Packing.PalletsSort;
using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PlacementOptimization.Decorators;

public class PlacementBySizeDecorator : PlacementDecorator
{
    public PlacementBySizeDecorator(IPlacement first) : base(first)
    {
        
    }
    public override CalculationResult Placement(SortResult request)
	{
		CalculationResult result = _first.Placement(request);
		request.Result.SelectMany(x => x.Pairs).SelectMany(x => x.Elements = x.Elements.OrderBy(x => x.GetLength(x.Rotation)).ToList());
		return result;
	}
}
