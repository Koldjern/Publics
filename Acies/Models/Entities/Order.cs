using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class Order
{
    public int Id { get; set; }
    public string Delivery { get; set; } = "";
    public virtual List<Element> Elements { get; set; } = new List<Element>();
	public virtual List<Group> Groups { get; set; } = new List<Group>();
}
