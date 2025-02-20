using Models.Entities;

namespace PresentationLayer.Models;

public class PlacedElementViewModel : PlacedElement
{
	public Guid PalletId { get; set; }
    public PlacedElementViewModel() : base()
    {
        
    }
	public PlacedElementViewModel(PlacedElement element) : base(element)
	{

	}
	public PlacedElementViewModel(Element element) : base(element)
	{

	}
}
