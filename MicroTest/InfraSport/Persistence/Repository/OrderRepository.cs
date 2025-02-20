using AppSport.Persistence.Repository;
using Domain.Entities;
using InfraSport.Persistence.EF;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Repository;

public class OrderRepository : IOrderRepository
{
	private readonly ISportEFContext _context;

	public OrderRepository(ISportEFContext context)
	{
		_context = context;
	}

	public async Task<Order?> FindOrder(int id)
	{
		return await _context.Orders.FindAsync(id);
	}

	public async Task<IEnumerable<Order>> Orders()
	{
		return await _context.Orders.Include(o => o.Lines).ThenInclude(cl => cl.Product).ToListAsync();
	}

	public async Task<Order?> AddOrder(Order order)
	{
		((DbContext)_context).AttachRange(order.Lines.Select(l => l.Product));
		if (order.OrderID == 0)
		{
			_context.Orders.Add(order);
		}
		return await _context.SaveDB() > 0 ? order : null; 
	}
	public async Task<Order?> EditOrder(Order order)
	{
		Order? orderFound = await FindOrder(order.OrderID);
		if(orderFound == null)
			return null;
		orderFound.Line1 = order.Line1;
		orderFound.Line2 = order.Line2;
		orderFound.Line3 = order.Line3;
		orderFound.Zip = order.Zip;
		orderFound.City = order.City;
		orderFound.Country = order.Country;
		orderFound.State = order.State;
		orderFound.Name = order.Name;
		orderFound.Shipped = order.Shipped;
		return await _context.SaveDB() > 0 ? order : null;
	}
}
