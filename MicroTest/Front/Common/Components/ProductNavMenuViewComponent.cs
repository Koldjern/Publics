using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Microsoft.AspNetCore.Mvc;

namespace Front.Common.Components;

public class ProductNavMenuViewComponent : ViewComponent
{
    private readonly IProductRepository _productRepository;
    public ProductNavMenuViewComponent(IProductRepository productRepository)
    {
        _productRepository = productRepository;
    }
    public async Task<IViewComponentResult> InvokeAsync()
    {
		return View(await _productRepository.GetCategories());
    }
}
