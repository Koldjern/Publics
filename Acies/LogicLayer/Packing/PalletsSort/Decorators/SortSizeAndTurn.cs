using Microsoft.AspNetCore.Cors.Infrastructure;
using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace LogicLayer.Packing.PalletsSort.Decorators;
//can fail if theres not any appropiate pallets
public class SortSizeAndTurn : IPalletSort
{
    private int _boardThickness;
    private int _maxTurnableWeight;
    private double _maxHeightWidthFactor;
    private bool _turnMaxHeightNonStackables;

	public SortSizeAndTurn(int boardThickness, int maxTurnableWeight, double maxHeightWidthFactor, bool turnMaxHeightNonStackables)
	{
		_boardThickness = boardThickness;
		_maxTurnableWeight = maxTurnableWeight;
		_maxHeightWidthFactor = maxHeightWidthFactor;
		_turnMaxHeightNonStackables = turnMaxHeightNonStackables;
	}

	public SortResult SortElements(CalculationRequest request)
	{
		IEnumerable<Pallet> pallets = request.Pallets.OrderBy(x => x.Length());
        SortResult sortResults = new(request);
		List<PlacedElement>[] groups = new List<PlacedElement>[request.Order.Groups.Count + 1];
		for (int i = 0; i < groups.Length; i++)
		{
			//add ungrouped elements
			if (i == 0)
			{
				groups[i] = new List<PlacedElement>(request.Order.Elements);
				sortResults.Result.Add(new Sort(pallets, null));
			}
			//add grouped elements
			else
			{
				groups[i] = new List<PlacedElement>(request.Order.Groups[i-1].Elements);
				sortResults.Result.Add(new Sort(pallets, request.Order.Groups[i-1]));
			}
		}
		for (int i = 0; i < groups.Length; i++)
			foreach (var element in groups[i])
			{
				PalletAndElements? x = Smallest(sortResults.Result[i], element, Rotation.X);
				PalletAndElements? y = Smallest(sortResults.Result[i], element, Rotation.Y);
				PalletAndElements? chosen = Which(x, y, element);
				//if no pallet was qualifiable
				if (chosen == null)
					throw new NoEligiblePalletException(element);
				//adds the element to the pallet 
				chosen.Elements.Add(element);
			}
		return sortResults;
    }
	private PalletAndElements? Smallest(Sort pallets, Element element, Rotation rotation)
	{
		foreach (var pallet in pallets.Pairs)
		{
			if (element.GetLength(rotation) > pallet.Length() - _boardThickness)
				continue;
			if (element.GetHeight(rotation) > pallet.GetMaxHeight())
				continue;
			return pallet;
		}
		return null;
	}
	private PalletAndElements? Which(PalletAndElements? x, PalletAndElements? y, PlacedElement element)
	{
		//both null fail
		if (y == null && x == null)
			return null;
		//y null and if element is turnable
		if (y == null && x != null)
		{
			if (element.Turnable > 0 || Turnable(x, element))
			{
				element.Rotation = Rotation.X;
				return x;
			}
			return null;
		}
		//x null and if element is turnable
		if (x == null && y != null)
		{
			//if not turnable then the element dosent fit any, more pallets has to be added 
			if(element.Turnable < 0 || Turnable(y, element))
			{
				element.Rotation = Rotation.Y;
				return y;
			}
			return null;
		}
		//testing factor values
		if (element.GetHeightWidthFactor(Rotation.Y) > _maxHeightWidthFactor)
		{
			element.Rotation = Rotation.Y;
			return y;
		}
		if (element.GetHeightWidthFactor(Rotation.X) > _maxHeightWidthFactor)
		{
			element.Rotation = Rotation.X;
			return x;
		}
		if (x.Length() < y.Length())
		{
			//x is the best choice
			element.Rotation = Rotation.X;
			return x;
		}
		if (x.Length() > y.Length())
		{
			//y is the best choice
			element.Rotation = Rotation.Y;
			return y;
		}
		//if x == y then select the side which benefits the element the most, Low factor is best
		if (element.GetHeightWidthFactor(Rotation.X) > element.GetHeightWidthFactor(Rotation.Y))
		{
			element.Rotation = Rotation.Y;
			return y;
		}
		else
		{
			element.Rotation = Rotation.X;
			return x;
		}
	}
	private bool Turnable(Pallet y, PlacedElement element)
	{
		if(element.Weight > _maxTurnableWeight)
			return false;
		if(!_turnMaxHeightNonStackables && element.Turnable == 0 && element.GetHeight(Rotation.Y) > y.MaxHeight)
			return false;
		return true;
	}
}
