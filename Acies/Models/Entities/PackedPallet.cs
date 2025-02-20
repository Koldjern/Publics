using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class PackedPallet : Pallet
{ 
	public List<List<PlacedElement>> Elements { get; set; }
    public PackedPallet()
    {
        Elements = Setup();
	}
    public PackedPallet(Pallet other) : base(other)
    {
		Elements = Setup();
	}
	private List<List<PlacedElement>> Setup()
    {
		if (SpecialCargo)
			return new List<List<PlacedElement>>();
		else
        {
			Elements = new List<List<PlacedElement>>();
            for (int i = 0; i < Spots; i++)
                Elements.Add(new List<PlacedElement>());
            return Elements;
        }
	}
	public int SpotsLeft()
	{
		return Elements.Where(x => x.Count == 0).Count();
	}
    public int WeightAtSpot(int spot)
    {
        return Elements[spot].Sum(x => x.Weight);
    }
	public int WeightAtSpot(List<PlacedElement> spot)
	{
		return spot.Sum(x => x.Weight);
	}
	public int HeightAtSpot(List<PlacedElement> spot)
	{
		return spot.Sum(x => x.GetHeight(x.Rotation));
	}
}
