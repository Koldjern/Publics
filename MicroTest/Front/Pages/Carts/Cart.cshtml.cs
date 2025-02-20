using AppSport.Persistence.Repository;
using Domain.Entities;
using Front.Common;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Front.Models.Common;

namespace Front.Pages.Carts;

public class CartModel : PageModel
{
	private IProductRepository _repository;
	public CartModel(IProductRepository repo, SessionHolder<Cart> cart)
	{
		_repository = repo;
		Cart = cart.GetValue();
	}
	public Cart? Cart { get; set; }
	public string ReturnUrl { get; set; } = "/";
	public void OnGet(string returnUrl)
	{
		ReturnUrl = returnUrl ?? "/";
	}
	public async Task<IActionResult> OnPost(long productId, string returnUrl)
	{
		Product? product = await _repository.GetAsync(productId);
		
		if (product != null)
		{
			Cart?.AddItem(product, 1);
		}
		return RedirectToPage(new { returnUrl = returnUrl });
	}
}