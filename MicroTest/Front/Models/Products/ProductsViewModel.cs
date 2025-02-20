using Domain.Entities;
using Front.Models.Common;

namespace Front.Models.Products;

public class ProductsViewModel : PagedParentViewModel<Product>
{
	public string? CurrentCategory { get; set; }
    public ProductsViewModel() : base()
    {
        
    }
    public ProductsViewModel(IEnumerable<Product> products, PagingInfo pagingInfo, string? currentCategory) 
        : base(products, pagingInfo)
    {
        CurrentCategory = currentCategory;
    }
}
