using Moq;
using Models.Entities;
using DataLayer.Orders;
using LogicLayer.Orders;

public class OrderServiceTests
{
    [Fact]
    public void GetOrders_ShouldReturnAllOrders()
    {
        // Arrange
        var mockRepository = new Mock<IOrderRepository>();
        var orders = new List<Order>
        {
            new Order { Id = 1 },
            new Order { Id = 2 }
        };

        mockRepository.Setup(repo => repo.GetOrders()).Returns(orders);

        var service = new OrderService(mockRepository.Object);

        // Act
        var result = service.GetOrders();

        // Assert
        Assert.NotNull(result);
        Assert.Equal(orders.Count, result.Count());
        Assert.Equal(orders, result);
    }

    [Fact]
    public void GetOrder_ShouldReturnOrder_WhenOrderExists()
    {
        // Arrange
        var mockRepository = new Mock<IOrderRepository>();
        var order = new Order { Id = 1 };

        mockRepository.Setup(repo => repo.GetOrder(1)).Returns(order);

        var service = new OrderService(mockRepository.Object);

        // Act
        var result = service.GetOrder(1);

        // Assert
        Assert.NotNull(result);
        Assert.Equal(order, result);
    }

    [Fact]
    public void GetOrder_ShouldReturnNull_WhenOrderDoesNotExist()
    {
        // Arrange
        var mockRepository = new Mock<IOrderRepository>();
        mockRepository.Setup(repo => repo.GetOrder(It.IsAny<int>())).Returns((Order?)null);

        var service = new OrderService(mockRepository.Object);

        // Act
        var result = service.GetOrder(99);

        // Assert
        Assert.Null(result);
    }
}
