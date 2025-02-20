using AppSport.Persistence.Repository;
using Domain.Entities;
using InfraSport.Persistence.Dynamo.Mapping;
using Infrastructure.Sql.Dynamo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Dynamo.Repository;

internal class OrderDynRepository : IOrderRepository
{
	private readonly IDynamoDBAccess _db;
	public OrderDynRepository(IDynamoDBAccess db)
	{
		_db = db;
	}

	public async Task<Order?> FindOrder(int id)
	{
		return await _db.QueryAsync<OrderDyn, int>(id);
	}

	public async Task<IEnumerable<Order>> Orders()
	{
		return await _db.QueryAllAsync<OrderDyn>("Order");
	}

	public async Task<Order?> AddOrder(Order order)
	{
		OrderDyn orderDyn = new OrderDyn(order)
		{
			OrderID = new Random().Next(1000000)
		};
		return await _db.InsertOrUpdateAsync<OrderDyn>(orderDyn) ? orderDyn : null;
	}
	public async Task<Order?> EditOrder(Order order)
	{
		OrderDyn orderDyn = new OrderDyn(order);
		return await _db.InsertOrUpdateAsync<OrderDyn>(orderDyn) ? orderDyn : null;
	}
}
