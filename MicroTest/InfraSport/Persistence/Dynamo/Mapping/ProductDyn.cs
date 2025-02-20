using Amazon.DynamoDBv2.DataModel;
using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InfraSport.Persistence.Dynamo.Mapping;
[DynamoDBTable("SportStore")]
internal class ProductDyn : Product
{
	[DynamoDBHashKey(AttributeName = "ID")]
	public override long ProductID { get; set; }
	[DynamoDBProperty]
	public override string Name { get; set; } = String.Empty;
	[DynamoDBProperty]
	public override string Description { get; set; } = String.Empty;
	[DynamoDBProperty]
	public override decimal Price { get; set; }
	[DynamoDBProperty]
	public override string Category { get; set; } = String.Empty;
	[DynamoDBProperty]
	public string Entity { get; set; }
    public ProductDyn()
    {
        
    }
    public ProductDyn(Product other) : base(other) 
    {
        
    }
}
