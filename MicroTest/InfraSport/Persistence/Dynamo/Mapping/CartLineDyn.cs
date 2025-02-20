using Amazon.DynamoDBv2.DataModel;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Dynamo.Mapping;
[DynamoDBTable("SportStore")]
internal class CartLineDyn : CartLine
{
	[DynamoDBHashKey(AttributeName = "ID")]
	public new int CartLineID { get { return base.CartLineID; } set { base.CartLineID = value; } }
	[DynamoDBProperty]
	public new Product Product { get { return base.Product; } set { base.Product = value; } }
	[DynamoDBProperty]
	public new int Quantity { get { return base.Quantity; } set { base.Quantity = value; } }
	public CartLineDyn(int cartLineID, Product product, int quantity) : base(cartLineID, product, quantity)
	{
	}
	public CartLineDyn(CartLine other) : base(other)
	{
	}
	public CartLineDyn() : base()
	{
	}
}
