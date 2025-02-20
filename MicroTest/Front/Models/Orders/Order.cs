using Domain.Entities;
using Front.Models.Common;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using System.ComponentModel.DataAnnotations;

namespace Front.Models.Orders;

public class OrderViewModel : Order
{
	[BindNever]
	public new int OrderID { get { return base.OrderID; } set { base.OrderID = value; } }
	[BindNever]
	public new List<CartLine> Lines { get { return base.Lines; } set { base.Lines = value; } }
	[Required(ErrorMessage = "Please enter a name")]
	public new string? Name { get; set; }
	[Required(ErrorMessage = "Please enter the first address line")]
	public new string? Line1 { get { return base.Line1; } set { base.Line1 = value; } }
	public new string? Line2 { get { return base.Line2; } set { base.Line2 = value; } }
	public new string? Line3 { get { return base.Line3; } set { base.Line3 = value; } }
	[Required(ErrorMessage = "Please enter a city name")]
	public new string? City { get { return base.City; } set { base.City = value; } }
	[Required(ErrorMessage = "Please enter a state name")]
	public new string? State { get { return base.State; } set { base.State = value; } }
	public new string? Zip { get { return base.Zip; } set { base.Zip = value; } }
	[Required(ErrorMessage = "Please enter a country name")]
	public new string? Country { get { return base.Country; } set { base.Country = value; } }
	public new bool GiftWrap { get { return base.GiftWrap; } set { base.GiftWrap = value; } }
	[BindNever]
	public new bool Shipped { get { return base.Shipped; } set { base.Shipped = value; } }
}
