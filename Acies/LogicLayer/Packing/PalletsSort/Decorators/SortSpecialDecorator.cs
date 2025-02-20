using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort.Decorators;

public class SortSpecialDecorator : SortingDecorator
{
	private int _boardThickness;
	public SortSpecialDecorator(IPalletSort first, int boardThickness) : base(first)
	{
		_boardThickness = boardThickness;
	}
	public override SortResult SortElements(CalculationRequest request)
	{
		var result = base.SortElements(request);
		foreach (var partResult in result.Result)
		{
			//find all with special selected
			var palletSelecteds = partResult.Pairs.SelectMany(x => x.Elements).Where(x => x.SpecialElement);
			if (palletSelecteds != null)
				foreach (var element in palletSelecteds)
					//get the element on the chosen pallet
					GetOnPallet(partResult, element.Id);
		}
		return result;
	}
	private void GetOnPallet(Sort sortResult, int id)
	{
		for (int i = 0; i < sortResult.Pairs.Count; i++)
		{
			//find which pallet element is on
			var element = sortResult.Pairs[i].Elements.Find(x => x.Id == id);
			if (element == null)
				continue;
			//check if the pallet is special
			if (element.Pallet != null && element.Pallet!.SpecialCargo)
				break;
			//add to special if special is found
			PalletAndElements? special = sortResult.Pairs.FirstOrDefault(x => x.SpecialCargo && element.GetLength(element.Rotation) + _boardThickness <= x.Dimensions.X + x.OverReach);
			if(special != null)
			{
				special.Elements.Add(element);
				//remove from wrong
				sortResult.Pairs[i].Elements.Remove(element);
			}
		}
	}
}
