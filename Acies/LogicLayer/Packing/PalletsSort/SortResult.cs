using Azure.Core;
using Models.Common;
using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.PalletsSort;

public class SortResult 
{
	public CalculationRequest Request { get; set; }
	public List<Sort> Result { get; set; } = new List<Sort>();
    public SortResult(CalculationRequest request)
    {
        Request = request;
    }
}
