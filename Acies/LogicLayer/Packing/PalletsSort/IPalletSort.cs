using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort;

public interface IPalletSort
{
    SortResult SortElements(CalculationRequest request);
}
