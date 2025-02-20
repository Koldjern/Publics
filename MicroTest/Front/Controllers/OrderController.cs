using AppSport.Persistence.Repository;
using Domain.Entities;
using Front.Models.Common;
using Front.Models.Orders;
using Microsoft.AspNetCore.Mvc;

namespace Front.Controllers;

public class OrderController : Controller	
{
	private IOrderRepository repository;
	private Cart cart;
	public OrderController(IOrderRepository repoService, SessionHolder<Cart> cartService)
	{
		repository = repoService;
		cart = cartService.GetValue();
	}
	public ViewResult Checkout() => View(new OrderViewModel());
	[HttpPost]
	public async Task<IActionResult> Checkout(Order order)
	{
		if (cart.Lines.Count() == 0)
		{
			ModelState.AddModelError("", "Sorry, your cart is empty!");
		}
		if (ModelState.IsValid)
		{
			order.Lines = cart.Lines.ToList();
			Order? result = await repository.AddOrder(order);
			cart.Clear();
			return RedirectToPage("/Carts/Completed", new { orderId = result?.OrderID });
		}
		else
		{
			return View();
		}
	}
}
