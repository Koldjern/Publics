using Azure.Core;
using LogicLayer.Packing.PalletsSort;
using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.SpotCalculations;

public abstract class SpotDecorator : ISpotCalculations
{
	protected ISpotCalculations _first;
	protected SpotDecorator(ISpotCalculations first)
	{
		_first = first;
	}

	public virtual CalculationResult SpotCalculate(CalculationResult result)
	{
		return _first.SpotCalculate(result);
	}
}
