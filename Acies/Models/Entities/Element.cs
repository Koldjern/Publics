using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Models.Common;

namespace Models.Entities;

public class Element
{
    public virtual int Id { get; set; }
    public virtual bool SpecialElement { get; set; }
    public virtual int Weight { get; set; }
    public virtual Dimensions3D Dimensions { get; set; } = new Dimensions3D();
    public virtual string? Batch { get; set; }
    public virtual bool Stackable { get; set; }
    public virtual int Quantity { get; set; }
    public Element()
    {
        
    }
    public Element(Element other)
    {
        Id = other.Id;
		SpecialElement = other.SpecialElement;
		Weight = other.Weight;
		Dimensions = other.Dimensions;
		Batch = other.Batch;
		Stackable = other.Stackable;
		Quantity = other.Quantity;
    }
    public int GetLength(Rotation rotation)
	{
		return rotation == Rotation.X ? Dimensions.X : Dimensions.Y;
	}
	public int GetHeight(Rotation rotation)
	{
		return rotation == Rotation.X ? Dimensions.Y : Dimensions.X;
	}
	public double GetHeightWidthFactor(Rotation rotation)
	{
		if(rotation == Rotation.X)
			return (double)Dimensions.Y / Dimensions.X;
		else								 
			return (double)Dimensions.X / Dimensions.Y;
	}
}
