using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities.Settings;

public class PlacementSettings
{
    public int MaxLayers { get; set; }
    public int MaxSpots { get; set; }
    public int MaxTurnableWeight { get; set; }
    //(Width/Height)
    public double MaxHeightWidthFactor { get; set; }
    public bool FactorOnlySingles { get; set; }
    public int MaxHeightStacking { get; set; }
    public int EndBoardThickness { get; set; }
    public int MaxStackElementWeight { get; set; }
    public bool TurnMaxHeightNonStackables { get; set; }
}
