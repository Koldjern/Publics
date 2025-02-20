using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.SpotCalculations.Decorators;

public class SpotCalculationByWeight : ISpotCalculations
{
	public CalculationResult SpotCalculate(CalculationResult result)
	{
		foreach (var pallet in result.PackedGroups.SelectMany(x => x.PackedPallets))
		{
			Calculate(pallet);
		}
		return result;
	}
	private void Calculate(PackedPallet pallet)
	{
		//heaviest first
		pallet.Elements = pallet.Elements.OrderBy(x => x.Sum(element => element.Weight)).ToList();
		switch (pallet.Elements.Count)
		{
			case 3:
				{
					//3, 1, 2
					var heavier = pallet.Elements[0];
					var lighter = pallet.Elements[1];
					pallet.Elements[0] = lighter;
					pallet.Elements[1] = heavier;
					break;
				}
			case 4:
				{
					//4,1,2,3
					var heavy1 = pallet.Elements[0];
					var heavy2 = pallet.Elements[1];
					var light1 = pallet.Elements[2];
					var light2 = pallet.Elements[3];
					pallet.Elements[0] = light2;
					pallet.Elements[1] = heavy1;
					pallet.Elements[2] = heavy2;
					pallet.Elements[3] = light1;
					break;
				}
			case 5:
				{
					//2,5,1,4,3
					var heavy1 = pallet.Elements[0];
					var heavy2 = pallet.Elements[1];
					var mid = pallet.Elements[2];
					var light1 = pallet.Elements[3];
					var light2 = pallet.Elements[4];
					pallet.Elements[0] = heavy2;
					pallet.Elements[1] = light2;
					pallet.Elements[2] = heavy1;
					pallet.Elements[3] = light2;
					pallet.Elements[4] = mid;
					break;
				}
			case 6:
				{
					//6,4,1,2,3,5
					var heavy1 = pallet.Elements[0];
					var heavy2 = pallet.Elements[1];
					var mid1 = pallet.Elements[2];
					var mid2 = pallet.Elements[3];
					var light1 = pallet.Elements[4];
					var light2 = pallet.Elements[5];
					pallet.Elements[0] = light2;
					pallet.Elements[1] = mid2;
					pallet.Elements[2] = heavy1;
					pallet.Elements[3] = heavy2;
					pallet.Elements[4] = mid1;
					pallet.Elements[5] = light1;
					break;
				}
			//more can be made

		}
	}
}
