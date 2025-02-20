using LogicLayer.Packing.PalletsSort;
using Models.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.SpotCalculations;

public interface ISpotCalculations
{
    CalculationResult SpotCalculate(CalculationResult result);
}
