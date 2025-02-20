using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AppSport.Persistence.Repository;

public interface IProductRepository
{
	Task<IEnumerable<string>> GetCategories();
	Task<IEnumerable<Product>> GetAllAsync();
	Task<IEnumerable<Product>> GetPagedAsync(int nr, int amount, string? category);
	Task<Product?> GetAsync(long id);
	Task<bool> DeleteAsync(long id);
	Task<bool> UpdateAsync(Product product);
	Task<Product?> InsertAsync(Product product);
	Task<int> GetMaxAsync(string? category);
}
