using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Models.Entities.Settings;

public class LocalSettingsData
{
	public string FileTypes { get; set; } = "Json";
	public PlacementSettings PlacementSettings { get; set; } = null!;
}
