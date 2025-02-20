using Front.Models.Products;
using Front.Models.Students;

namespace Front.Models.Home;

public class IndexViewModel
{
	public ProductsViewModel Products { get; set; } = null!;
	public StudentsViewModel Students { get; set; } = null!;

}
