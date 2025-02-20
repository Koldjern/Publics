using DataLayer.Files.Settings;
using Models.Entities.Settings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Settings;

public interface ISettingsService
{
	LocalSettingsData? GetSettings();
	bool UpdateSettings(LocalSettingsData settings);
}
