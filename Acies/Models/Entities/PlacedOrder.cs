using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class PlacedOrder 
{
	public int Id { get; set; }
	public bool GroupByBatch { get; set; }
	public string Delivery { get; set; } = "";
	public List<PlacedElement> Elements { get; set; } = new List<PlacedElement>();
	public List<PlacedGroup> Groups { get; set; } = new List<PlacedGroup>();
    public PlacedOrder()
    {
    }
    public PlacedOrder(Order order)
    {
        Id = order.Id;
        Delivery = order.Delivery;
        Elements = order.Elements.Select(e => new PlacedElement(e)).ToList();
        Groups = order.Groups.Select(g => new PlacedGroup(g)).ToList();
    }

}
