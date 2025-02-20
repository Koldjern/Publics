using Models.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Packing;

public class NoEligiblePalletException : Exception
{
    public NoEligiblePalletException(PlacedElement element) : base($"Der er ikke tilføjet en palle der kan bære dette element, Id: {element.Id}, Længde: {element.GetLength(element.Rotation)}")
    {
        
    }
}
