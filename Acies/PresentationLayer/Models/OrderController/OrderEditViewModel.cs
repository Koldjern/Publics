using Models.Entities;

namespace PresentationLayer.Models.OrderController;

public class OrderEditViewModel
{
    public int OrderId { get; set; }
	public bool GroupByBatch { get; set; }
	public IEnumerable<Pallet> Pallets { get; set; } = null!;
    public PlacedOrderViewModel PlacedOrder { get; set; } = new PlacedOrderViewModel();
    public OrderEditViewModel(IEnumerable<Pallet> pallets, PlacedOrderViewModel placedOrder, int orderId)
    {
        OrderId = orderId;
        Pallets = pallets;
        PlacedOrder = placedOrder;
    }
    public OrderEditViewModel()
    {
        
    }
}
