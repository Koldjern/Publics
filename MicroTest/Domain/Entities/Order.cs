using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Entities;

public class Order
{
	public int OrderID { get; set; }
	public List<CartLine> Lines { get; set; } = new List<CartLine>();
	public string? Name { get; set; }
	public string? Line1 { get; set; }
	public string? Line2 { get; set; }
	public string? Line3 { get; set; }
	public string? City { get; set; }
	public string? State { get; set; }
	public string? Zip { get; set; }
	public string? Country { get; set; }
	public bool GiftWrap { get; set; }
	public bool Shipped { get; set; }

	public Order(int orderID, List<CartLine> lines, string? name, string? line1, string? line2, string? line3, string? city, string? state, string? zip, string? country, bool giftWrap, bool shipped)
	{
		OrderID = orderID;
		Lines = lines;
		Name = name;
		Line1 = line1;
		Line2 = line2;
		Line3 = line3;
		City = city;
		State = state;
		Zip = zip;
		Country = country;
		GiftWrap = giftWrap;
		Shipped = shipped;
	}
    public Order(Order other) : this(other.OrderID, other.Lines, other.Name, 
		other.Line1, other.Line2, other.Line3, other.City, other.State, 
		other.Zip, other.Country, other.GiftWrap, other.Shipped)
    {
        
    }
    public Order()
    {
        
    }
}
