using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort.Decorators;

public class SortChosenPalletDecorator : SortingDecorator
{

	public SortChosenPalletDecorator(IPalletSort first) : base(first)
	{

	}
	public override SortResult SortElements(CalculationRequest request)
	{
		var result = base.SortElements(request);
		foreach (var partResult in result.Result)
		{
			//find all with pallet selected
			var palletSelecteds = partResult.Pairs.SelectMany(x => x.Elements).Where(x => x.Pallet != null);
			if (palletSelecteds != null)
				foreach (var element in palletSelecteds)
					//get the element on the chosen pallet
					GetOnPallet(partResult, element.Pallet!.Id, element.Id);
		}
		return result;
	}
	private void GetOnPallet(Sort sortResult, Guid pId, int id)
	{
		for (int i = 0; i < sortResult.Pairs.Count; i++)
		{
			//find which pallet element is on
			var element = sortResult.Pairs[i].Elements.Find(x => x.Id == id);
			if (element == null)
				continue;
			//check if the pallet is the chosen
			if (element.Pallet!.Id == sortResult.Pairs[i].Id)
				break;
			//add to chosen
			sortResult.Pairs.First(x => x.Id == pId).Elements.Add(element);
			//remove from wrong
			sortResult.Pairs[i].Elements.Remove(element);
		}
	}
}
