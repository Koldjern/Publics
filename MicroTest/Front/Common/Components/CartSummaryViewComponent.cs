using Front.Models.Common;
using Microsoft.AspNetCore.Mvc;

namespace Front.Common.Components;

public class CartSummaryViewComponent : ViewComponent
{
    private Cart cart;
    public CartSummaryViewComponent(SessionHolder<Cart> cartService)
    {
        cart = cartService.GetValue();
    }
    public IViewComponentResult Invoke()
    {
        return View(cart);
    }
}
