using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort;

public abstract class SortingDecorator : IPalletSort
{
	protected IPalletSort _first;
	protected SortingDecorator(IPalletSort first)
	{
		_first = first;
	}

	public virtual SortResult SortElements(CalculationRequest request)
	{
		return _first.SortElements(request);
	}
}
