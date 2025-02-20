using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Entities;

public class CartLine
{
	public int CartLineID { get; set; }
	public Product Product { get; set; } = new();
	public int Quantity { get; set; }
    public CartLine(int cartLineID, Product product, int quantity)
    {
        CartLineID = cartLineID;
        Product = product;
        Quantity = quantity;
    }
	public CartLine(CartLine other) : this(other.CartLineID, other.Product, other.Quantity)
	{

	}
	public CartLine()
    {
        
    }
}
