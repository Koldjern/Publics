using AppSport.Persistence.Repository;
using Domain.Entities;
using Infrastructure.Api;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraFront;

class OrderRepository : IOrderRepository
{
	private readonly IApiAccess _apiAccess;
	public OrderRepository(IApiAccess apiAccess, IConfiguration configuration)
	{
		_apiAccess = apiAccess;
		_apiAccess.Configure(configuration, "SportStore");
	}

	public async Task<Order?> FindOrder(int id)
	{
		return await _apiAccess.QuerySingle<Order>($"Orders/id/{id}");
	}

	public async Task<IEnumerable<Order>> Orders()
	{
		return await _apiAccess.QueryMultiple<Order>("Orders");
	}

	public async Task<Order?> AddOrder(Order order)
	{
		return await _apiAccess.PostData<Order?, Order>("Orders", order);
	}

	public async Task<Order?> EditOrder(Order order)
	{
		return await _apiAccess.UpdateData<Order?, Order>("Orders", order);
	}
}
