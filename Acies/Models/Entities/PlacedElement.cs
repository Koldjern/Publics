using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class PlacedElement : Element
{

    public Pallet? Pallet { get; set; }
    public bool SpecialPallet { get; set; } = false;
    public virtual Rotation Rotation { get; set; } = Rotation.X;
    public virtual int Turnable { get; set; }
    public int MaxSpotsOnPallet { get; set; } = 0;
    public PlacedElement() : base()
    {
    }
    public PlacedElement(Element other) : base(other)
    {
    }
    public PlacedElement(PlacedElement other) : base(other)
	{
        MaxSpotsOnPallet = other.MaxSpotsOnPallet;
		Pallet = other.Pallet;
        SpecialPallet = other.SpecialPallet;
        Rotation = other.Rotation;
		Turnable = other.Turnable;
	}
}
public enum Rotation
{
    X, Y
}

