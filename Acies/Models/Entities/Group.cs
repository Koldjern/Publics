﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities;

public class Group
{
	public string Description { get; set; } = "";
	public List<Element> Elements { get; set; } = new List<Element>();
}
