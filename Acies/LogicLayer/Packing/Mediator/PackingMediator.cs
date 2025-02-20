using DataLayer.Files.Settings;
using LogicLayer.Packing.PalletsSort;
using LogicLayer.Packing.PalletsSort.Decorators;
using LogicLayer.Packing.Placement.Decorators;
using LogicLayer.Packing.PlacementOptimization;
using LogicLayer.Packing.PlacementOptimization.Decorators;
using LogicLayer.Packing.SpotCalculations;
using LogicLayer.Packing.SpotCalculations.Decorators;
using Models.Common;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing.Decorations;

public class PackingMediator : IPackingMediator
{
	//first
	private SortingDecorator _sorter;
	//second
	private readonly PlacementDecorator _bySize;
	private readonly PlacementDecorator _bySeries;
	//third
	private ISpotCalculations _optimizePlacement;

    public PackingMediator(ISettingsRepository localSettings)
    {
		var settings = localSettings.GetSettings()!.PlacementSettings;
		var sort = new SortSizeAndTurn(settings.EndBoardThickness, settings.MaxTurnableWeight, settings.MaxHeightWidthFactor, settings.TurnMaxHeightNonStackables);
		_sorter = new SortChosenPalletDecorator(new SortSpecialDecorator(sort, settings.EndBoardThickness));
		_bySeries = new PlacementStackerDecorator(new PlacementBySeriesDecorator(new PlacementQuantity()), settings.MaxSpots, settings.FactorOnlySingles, settings.MaxHeightStacking, settings.MaxLayers, settings.MaxStackElementWeight);
		_bySize = new PlacementStackerDecorator(new PlacementBySizeDecorator(new PlacementQuantity()), settings.MaxSpots, settings.FactorOnlySingles, settings.MaxHeightStacking, settings.MaxLayers, settings.MaxStackElementWeight);
		_optimizePlacement = new SpotCalculationByWeight();
	}
	public CalculationResult CalculatePacking(CalculationRequest request)
	{
		SortResult first = _sorter.SortElements(request);
		CalculationResult result;
		if(request.GroupByBatch)
			result = _bySeries.Placement(first);
		else
			result = _bySize.Placement(first);
		return _optimizePlacement.SpotCalculate(result);
	}
}
