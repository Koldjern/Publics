using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class PlacedGroup : Group
{
	public new List<PlacedElement> Elements { get; set; } = new List<PlacedElement>();
    public PlacedGroup()
    {
        
    }
    public PlacedGroup(Group group)
    {
        Description = group.Description;
        Elements = group.Elements.Select(e => new PlacedElement(e)).ToList();
    }
}
