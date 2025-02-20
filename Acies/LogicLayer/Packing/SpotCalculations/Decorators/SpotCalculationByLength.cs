using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.SpotCalculations.Decorators
{
	class SpotCalculationByLength : ISpotCalculations
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
			//longest first
			pallet.Elements = pallet.Elements.OrderBy(x => x[0].GetLength(x[0].Rotation)).ToList();

			switch (pallet.Elements.Count)
			{
				case 3:
					{
						//1, 3, 2
						var longest1 = pallet.Elements[0];
						var longest2 = pallet.Elements[1];
						var longest3 = pallet.Elements[2];
						pallet.Elements[1] = longest3;
						pallet.Elements[2] = longest2;
						break;
					}
				case 4:
					{
						//1,4,3,2
						var longest1 = pallet.Elements[0];
						var longest2 = pallet.Elements[1];
						var longest3 = pallet.Elements[2];
						var longest4 = pallet.Elements[3];
						pallet.Elements[3] = longest2;
						pallet.Elements[1] = longest4;
						break;
					}
				case 5:
					{
						//2,5,1,4,3
						var longest1 = pallet.Elements[0];
						var longest2 = pallet.Elements[1];
						var longest3 = pallet.Elements[2];
						var longest4 = pallet.Elements[3];
						var longest5 = pallet.Elements[4];
						pallet.Elements[4] = longest2;
						pallet.Elements[1] = longest5;
						break;
					}
				case 6:
					{
						//6,4,1,2,3,5
						var longest1 = pallet.Elements[0];
						var longest2 = pallet.Elements[1];
						var longest3 = pallet.Elements[2];
						var longest4 = pallet.Elements[3];
						var longest5 = pallet.Elements[4];
						var longest6 = pallet.Elements[5];
						pallet.Elements[5] = longest2;
						pallet.Elements[1] = longest6;
						break;
					}
					//more can be made
			}
		}
	}
}
