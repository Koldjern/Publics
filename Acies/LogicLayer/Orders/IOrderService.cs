using Models.Entities;


namespace LogicLayer.Orders
{
    public interface IOrderService
    {
        IEnumerable<Order>? GetOrders();

        Order? GetOrder(int id);

    }
}
