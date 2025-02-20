using Models.Entities;

namespace PresentationLayer.Models;

public class PlacedGroupViewModel : PlacedGroup
{
	public new List<PlacedElementViewModel> Elements { get; set; } = new List<PlacedElementViewModel>();
	public PlacedGroupViewModel()
	{

	}
	public PlacedGroupViewModel(Group group)
	{
		Description = group.Description;
		Elements = group.Elements.Select(e => new PlacedElementViewModel(e)).ToList();
	}
}
