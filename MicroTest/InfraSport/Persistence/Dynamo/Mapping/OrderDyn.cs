using Amazon.DynamoDBv2.DataModel;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Dynamo.Mapping;
[DynamoDBTable("SportStore")]
internal class OrderDyn : Order
{
	[DynamoDBHashKey(AttributeName = "ID")]
	public new int OrderID { get { return base.OrderID; } set { base.OrderID = value; } }
	[DynamoDBProperty]
	public new List<CartLine> Lines { get { return base.Lines; } set { base.Lines = value; } }
	[DynamoDBProperty]
	public new string? Name { get { return base.Name; } set { base.Name = value; } }
	[DynamoDBProperty]
	public new string? Line1 { get { return base.Line1; } set { base.Line1 = value; } }
	[DynamoDBProperty]
	public new string? Line2 { get { return base.Line2; } set { base.Line2 = value; } }
	[DynamoDBProperty]
	public new string? Line3 { get { return base.Line3; } set { base.Line3 = value; } }
	[DynamoDBProperty]
	public new string? City { get { return base.City; } set { base.City = value; } }
	[DynamoDBProperty]
	public new string? State { get { return base.State; } set { base.State = value; } }
	[DynamoDBProperty]
	public new string? Zip { get { return base.Zip; } set { base.Zip = value; } }
	[DynamoDBProperty]
	public new string? Country { get { return base.Country; } set { base.Country = value; } }
	[DynamoDBProperty]
	public new bool GiftWrap { get { return base.GiftWrap; } set { base.GiftWrap = value; } }
	[DynamoDBProperty]
	public new bool Shipped { get { return base.Shipped; } set { base.Shipped = value; } }
	[DynamoDBProperty]
	public string Entity { get; set; }


	public OrderDyn(int orderID, List<CartLine> lines, string? name, 
		string? line1, string? line2, string? line3, string? city, string? state, 
		string? zip, string? country, bool giftWrap, bool shipped) 
		: base (orderID, lines, name, line1, line2, line3,
			city, state, zip, country, giftWrap, shipped)
	{
	}
	public OrderDyn(Order other) : base(other)
	{

	}
	public OrderDyn() : base() 
	{

	}
}
