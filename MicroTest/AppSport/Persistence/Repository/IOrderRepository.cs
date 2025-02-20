using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppSport.Persistence.Repository;

public interface IOrderRepository
{
	Task<IEnumerable<Order>> Orders();
	Task<Order?> AddOrder(Order order);
	Task<Order?> EditOrder(Order order);
	Task<Order?> FindOrder(int id);
}
