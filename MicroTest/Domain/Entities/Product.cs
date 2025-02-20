using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Entities;

public class Product
{
	public virtual long ProductID { get; set; }
	public virtual string Name { get; set; } = String.Empty;
	public virtual string Description { get; set; } = String.Empty;
	public virtual decimal Price { get; set; }
	public virtual string Category { get; set; } = String.Empty;
    public Product()
    {
        
    }
    public Product(long productID, string name, string description, decimal price, string category)
    {
        ProductID = productID;
        Name = name;
        Description = description;
        Price = price;
        Category = category;
    }
    public Product(Product other) : 
        this(other.ProductID, other.Name, other.Description, other.Price, other.Category)
    {
        
    }
}
