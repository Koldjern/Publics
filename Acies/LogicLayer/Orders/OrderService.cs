using DataLayer.Orders;
using LogicLayer.Orders;
using Models.Entities;

namespace LogicLayer.Orders;

public class OrderService : IOrderService
{
    private IOrderRepository _repository;

    public OrderService(IOrderRepository repository)
    {
        _repository = repository;
    }

    public IEnumerable<Order>? GetOrders()
    {
        return _repository.GetOrders();
    }

    public Order? GetOrder(int id)
    {
        return _repository.GetOrder(id);
    }
}

