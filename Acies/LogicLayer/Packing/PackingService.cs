using DataLayer.Files.Settings;
using LogicLayer.Packing.Decorations;
using Models.Common;
using Models.Entities.Settings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing;

public class PackingService : IPackingService
{
    private readonly IPackingMediator _mediator;
    public PackingService(IPackingMediator mediator)
    {
        _mediator = mediator;
    }
    public CalculationResult PackPallets(CalculationRequest request)
	{
        //RoyalGuardClauseBrigade Commit Crimes against validation

        return _mediator.CalculatePacking(request);
	}
}
