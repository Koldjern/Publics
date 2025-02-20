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

public class ProductRepository : IProductRepository
{
	private readonly ISportEFContext _context;

	public ProductRepository(ISportEFContext context)
	{
		_context = context;
	}
    //remove will do cacade on keys, use for singles
    //ExecuteDeleteAsync used for bulk, will only if it is set up to do so
    public async Task<bool> DeleteAsync(long id)
	{
		if (await _context.Products.FindAsync(id) is not Product product)
			return false;
        _context.Products.Remove(product);
		var result = await _context.SaveDB();
		if (result > 0)
			return true;
		return false;
	}

	public async Task<Product?> GetAsync(long id)
	{
		return await _context.Products.FindAsync(id);
	}

	public async Task<Product?> InsertAsync(Product product)
	{
		if (await _context.Products.FindAsync(product.ProductID) != null)
			return null;
		_context.Products.Add(product);
		var result = await _context.SaveDB();
		if (result > 0)
			return product;
		return null;
	}

	public async Task<bool> UpdateAsync(Product product)
	{
        if (await _context.Products.FindAsync(product.ProductID) is not Product update)
            return false;

		update.Name = product.Name;
		update.Price = product.Price;
		update.Description = product.Description;
		update.Category = product.Category;

		return await _context.SaveDB() > 0;
	}

	public async Task<IEnumerable<Product>> GetAllAsync()
	{
		return await _context.Products.ToArrayAsync();
	}

	public async Task<IEnumerable<Product>> GetPagedAsync(int nr, int amount, string? category = null)
	{
		return await _context.Products
			.Where(p => p.Category == category || category == null)
			.Skip((nr - 1) * amount)
			.Take(amount)
			.ToArrayAsync();
	}

	public async Task<int> GetMaxAsync(string? category = null)
	{
		return await _context.Products
			.Where(p => p.Category == category || category == null)
			.CountAsync();
	}

	public async Task<IEnumerable<string>> GetCategories()
	{
		return await _context.Products
			.Select(x => x.Category)
			.Distinct()
			.OrderBy(x => x)
			.ToArrayAsync();
	}
}
