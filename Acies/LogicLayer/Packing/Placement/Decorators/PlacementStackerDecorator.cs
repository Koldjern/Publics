using LogicLayer.Packing.PalletsSort;
using Models.Common;
using Models.Entities;

namespace LogicLayer.Packing.PlacementOptimization.Decorators;

public class PlacementStackerDecorator : PlacementDecorator
{
	private readonly int _maxSpots;
	private readonly bool _onlySingles;
	private readonly int _maxHeightStacking;
	private readonly int _maxLayers;
	private readonly int _maxStackElementWeight;
	public PlacementStackerDecorator(IPlacement first, int maxSpots, bool onlySingles, int maxHeightStacking, int maxLayers, int maxStackElementWeight) : base(first)
	{
		_maxStackElementWeight = maxStackElementWeight;
		_maxLayers = maxLayers;
		_maxSpots = maxSpots;
		_onlySingles = onlySingles;
		_maxHeightStacking = maxHeightStacking;
	}

	public override CalculationResult Placement(SortResult sorting)
	{
		//items will after this be sorted by importance
		var result = base.Placement(sorting);
		foreach (var sort in sorting.Result)
		{
			if (sort.Group != null)
				result.PackedGroups.Add(PackGroup(sort));
		}
		var rest = sorting.Result.Find(x => x.Group == null);
		if (rest != null)
		{
			PackRest(rest, result.PackedGroups);
			if (rest.Pairs.Sum(x => x.Elements.Count()) > 0)
			{
				result.PackedGroups.Add(PackGroup(rest));
			}
		}
		return result;
	}

	private void PackRest(Sort sortResult, List<GroupCalculation> groups)
	{
		sortResult.Pairs.ForEach(x => PackPallets(x, groups));
	}

	private GroupCalculation PackGroup(Sort sortResult)
	{
		GroupCalculation result = new() { Group = sortResult.Group };
		//loop of pallet and elements
		foreach (var pair in sortResult.Pairs)
		{
			PackPallets(pair, result);
		}
		return result;
	}
	private void PackPallets(PalletAndElements pair, List<GroupCalculation> groups)
	{
		foreach (var group in groups)
		{
			if (pair.Elements.Count > 0)
				AddRestToPallets(group.PackedPallets, pair.Elements);
		}
	}
	private void PackPallets(PalletAndElements pair, GroupCalculation result)
	{
		if (pair.Elements.Count > 0)
			result.PackedPallets.AddRange(AddElements(pair, pair.Elements));
	}
	private List<PackedPallet> AddElements(Pallet pallet, List<PlacedElement> elements)
	{
		List<PackedPallet> result = new List<PackedPallet>() { new PackedPallet(pallet) };
		foreach (var element in elements)
		{
			if (!PlaceElement(element, result))
			{
				result.Add(new PackedPallet(pallet));
				PlaceElement(element, result);
			}
		}
		return result;
	}
	private void AddRestToPallets(List<PackedPallet> pallets, List<PlacedElement> elements)
	{
		for (int i = 0; i < elements.Count;)
		{
			if (PlaceElement(elements[i], pallets))
				elements.RemoveAt(i);
			else
				i++;
		}
	}
	private bool PlaceElement(PlacedElement element, List<PackedPallet> pallets)
	{
		foreach (var pallet in pallets)
		{
			if (pallet.Elements.SelectMany(x => x).Select(y => y.Weight).Sum() > pallet.MaxWeight)
				return false;
			for (int i = 0; i < pallet.Elements.Count(); i++)
			{
				//is it possible to add the element in any way
				if (!CanPlace(element, pallet.Elements[i], pallet))
					continue;
				//if any other spots still need element and this already has
				if (pallet.Elements.Any(x => x.Count() == 0) && pallet.Elements[i].Count > 0)
					continue;
				//if this spot is empty
				if (element.MaxSpotsOnPallet > 0)
					for (int remove = pallet.SpotsLeft(); remove > element.MaxSpotsOnPallet; remove--)
						pallet.Elements.RemoveAt(pallet.Elements.Count() - 1);
				if (pallet.Elements[i].Count == 0)
					pallet.Elements[i].Add(element);
				//stack it, canplace ensures its possible
				else
					pallet.Elements[i] = Stack(element, pallet.Elements[i]);
				return true;
			}
		}
		return false;
	}
	private bool CanPlace(PlacedElement element, List<PlacedElement> spot, PackedPallet pallet)
	{
		return
			(element.MaxSpotsOnPallet == 0 || element.MaxSpotsOnPallet > pallet.SpotsLeft() || element.MaxSpotsOnPallet < pallet.SpotsLeft()) &&
			!(spot.Count >= _maxLayers) &&
			!(pallet.WeightAtSpot(spot) + element.Weight > pallet.MaxWeight) &&
			!(pallet.HeightAtSpot(spot) + element.GetHeight(element.Rotation) > pallet.MaxHeight) &&
			!(spot.Sum(x => x.GetHeight(x.Rotation)) > _maxHeightStacking) &&
			!(spot.Count > 0 && element.Weight > _maxStackElementWeight && spot.Any(x => x.Weight > _maxStackElementWeight)) &&
			!(!element.Stackable && spot.Count > 0 && !spot.Last().Stackable) &&
			!spot.Any(x => x.Weight > _maxStackElementWeight && x.GetLength(x.Rotation) < element.GetLength(element.Rotation)) &&
			!(spot.Count > 0 && !spot.Last().Stackable && spot.Last().GetLength(spot.Last().Rotation) < element.GetLength(element.Rotation));
	}

	private List<PlacedElement> Stack(PlacedElement element, List<PlacedElement> spot)
	{
		spot.Add(element);
		return spot.OrderByDescending(x => x.GetLength(x.Rotation)).ToList();
	}
}
