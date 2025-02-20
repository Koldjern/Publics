using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing;

public interface IPackingService
{
	CalculationResult PackPallets(CalculationRequest request);
}
