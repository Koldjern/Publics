using Models.Entities.Settings;
using System.ComponentModel.DataAnnotations;

namespace PresentationLayer.Models;

public class SettingsModel 
{
	[Range(1,7, ErrorMessage ="Imellem 1 og 7")]
	public int MaxLayers { get; set; }
	[Range(1, 10, ErrorMessage = "Imellem 1 og 10")]
	public int MaxSpots { get; set; }
	[Range(1, int.MaxValue, ErrorMessage = "Minimum 1")]
	public int MaxTurnableWeight { get; set; }
	[Range(1, int.MaxValue, ErrorMessage = "Minimum 1")]
	public double MaxHeightWidthFactor { get; set; }
	[Required]
	public bool FactorOnlySingles { get; set; }
	[Range(1, int.MaxValue, ErrorMessage = "Minimum 1")]
	public int MaxHeightStacking { get; set; }
	[Range(1, int.MaxValue, ErrorMessage = "Minimum 1")]
	public int EndBoardThickness { get; set; }
	[Range(1, int.MaxValue, ErrorMessage = "Minimum 1")]
	public int MaxStackElementWeight { get; set; }
	[Required]
	public bool TurnMaxHeightNonStackables { get; set; }
    public SettingsModel(LocalSettingsData data)
    {
        var settings = data.PlacementSettings;
		MaxLayers = settings.MaxLayers;
		MaxSpots = settings.MaxSpots;
		MaxTurnableWeight = settings.MaxTurnableWeight;
		MaxHeightWidthFactor = settings.MaxHeightWidthFactor;
		FactorOnlySingles = settings.FactorOnlySingles;
		MaxHeightStacking = settings.MaxHeightStacking;
		EndBoardThickness = settings.EndBoardThickness;
		MaxStackElementWeight = settings.MaxStackElementWeight;
		TurnMaxHeightNonStackables = settings.TurnMaxHeightNonStackables;
    }
	public SettingsModel()
	{

	}
	public LocalSettingsData ToData()
	{
		var data = new LocalSettingsData() { PlacementSettings = new PlacementSettings()};
		var settings = data.PlacementSettings;
		settings.MaxLayers = MaxLayers;
		settings.MaxSpots = MaxSpots;
		settings.MaxTurnableWeight = MaxTurnableWeight;
		settings.MaxHeightWidthFactor = MaxHeightWidthFactor;
		settings.FactorOnlySingles = FactorOnlySingles;
		settings.MaxHeightStacking = MaxHeightStacking;
		settings.EndBoardThickness = EndBoardThickness;
		settings.MaxStackElementWeight = MaxStackElementWeight;
		settings.TurnMaxHeightNonStackables = TurnMaxHeightNonStackables;
		return data;
	}
}
