using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Common;

public class CalculationResult
{
    public CalculationRequest CalculationRequest { get; set; }
	public List<GroupCalculation> PackedGroups { get; set; } = new();
    public CalculationResult(CalculationRequest calculationRequest)
    {
        CalculationRequest = calculationRequest;
    }
}
