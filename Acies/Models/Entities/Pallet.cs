using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Models.Common;

namespace Models.Entities;

public class Pallet
{
    public virtual Guid Id { get; set; } = Guid.NewGuid();
    public virtual string? Description { get; set; }
    public virtual Dimensions3D Dimensions { get; set; } = new Dimensions3D();
    public virtual string? PalletGroup { get; set; }
    public virtual string? PalletMaterial { get; set; }
    public virtual int Weight { get; set; }
    public virtual int MaxHeight { get; set; }
    public virtual int MaxWeight { get; set; }
    public virtual int OverReach { get; set; }
    public virtual int Spots { get; set; }
    public virtual bool SpecialCargo { get; set; }
    public virtual  int SpaceBetween { get; set; }
    public virtual bool InUse { get; set; }
    public Pallet()
    {
        
    }
	public Pallet(Guid id, string? description, Dimensions3D dimensions, string? palletGroup, string? palletMaterial, int weight, int maxHeight, int maxWeight, int overReach, int spots, bool specialCargo, int spaceBetween, bool inUse)
	{
		Id = id;
		Description = description;
		Dimensions = dimensions;
		PalletGroup = palletGroup;
		PalletMaterial = palletMaterial;
		Weight = weight;
		MaxHeight = maxHeight;
		MaxWeight = maxWeight;
		OverReach = overReach;
		Spots = spots;
		SpecialCargo = specialCargo;
		SpaceBetween = spaceBetween;
		InUse = inUse;
	}
    public Pallet(Pallet other)
        : this(other.Id, other.Description, other.Dimensions, other.PalletGroup, other.PalletMaterial, other.Weight, other.MaxHeight, other.MaxWeight, other.OverReach, other.Spots, other.SpecialCargo, other.SpaceBetween, other.InUse)
    {
    }
    public int Length()
    {
        return Dimensions.X + OverReach;
    }
	public int GetMaxHeight()
	{
		return MaxHeight - Dimensions.Y;
	}
}
