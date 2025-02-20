using Models.Common;
using Models.Entities;

namespace PresentationLayer.Models.OrderController;

public class ResultViewModel
{
	public CalculationResult Result { get; set; }
    public string Summary { get; set; }
    public List<string> PalletsSummary { get; set; }
	public List<string> ElementsSummary { get; set; }
	public ResultViewModel(CalculationResult result)
    {
        Result = result;
		//pallets summary
        //finds all the distinct types will be used to count on each for how many pallets of each there are 
        List<PackedPallet> pallets = result.PackedGroups.SelectMany(x => x.PackedPallets).DistinctBy(x => x.Id).ToList();
		PalletsSummary = new List<string>();
		foreach (var item in pallets)
        {
            int amount = result.PackedGroups.SelectMany(x => x.PackedPallets).Where(x => x.Id == item.Id).Count();
            PalletsSummary.Add($"Id : {item.Id}, Antal {amount}");
		}

		//elements summary
		//finds all the distinct types will be used to count on each for how many elements of each there are 
		List<PlacedElement> elements = result.PackedGroups.SelectMany(x => x.PackedPallets).SelectMany(x => x.Elements).SelectMany(x => x).DistinctBy(x => x.Id).ToList();
		ElementsSummary = new List<string>();
		foreach (var item in elements)
		{
			int amount = result.PackedGroups.SelectMany(x => x.PackedPallets).SelectMany(x => x.Elements).SelectMany(x => x).Where(x => x.Id == item.Id).Count();
			ElementsSummary.Add($"Id : {item.Id}, Antal {amount}");
		}


	}
}
