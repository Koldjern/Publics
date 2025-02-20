using LogicLayer.Packing.PalletsSort;
using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PlacementOptimization;

public abstract class PlacementDecorator : IPlacement
{
	protected IPlacement _first;
	protected PlacementDecorator(IPlacement first)
	{
		_first = first;
	}

	public virtual CalculationResult Placement(SortResult request)
	{
		return _first.Placement(request);
	}
}
