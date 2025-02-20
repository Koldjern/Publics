using Models.Entities;

namespace PresentationLayer.Models;

public class PlacedOrderViewModel 
{
	public int Id { get; set; }
	public string Delivery { get; set; } = "";
	public List<PlacedElementViewModel> Elements { get; set; } = new List<PlacedElementViewModel>();
	public List<PlacedGroupViewModel> Groups { get; set; } = new List<PlacedGroupViewModel>();
	public PlacedOrderViewModel()
	{
	}
	public PlacedOrderViewModel(Order order)
	{
		Id = order.Id;
		Delivery = order.Delivery;
		Elements = order.Elements.Select(e => new PlacedElementViewModel(e)).ToList();
		Groups = order.Groups.Select(g => new PlacedGroupViewModel(g)).ToList();
	}
}
