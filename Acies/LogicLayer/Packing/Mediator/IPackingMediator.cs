using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.Decorations;

public interface IPackingMediator
{
	CalculationResult CalculatePacking(CalculationRequest request);
}
