using Models.Entities;

namespace DataLayer.Orders;

public interface IOrderRepository
{
    IEnumerable<Order>? GetOrders();

    Order? GetOrder(int id);
}

