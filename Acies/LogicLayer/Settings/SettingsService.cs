using DataLayer.Files.Settings;
using Models.Entities.Settings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LogicLayer.Settings;

public class SettingsService : ISettingsService
{
	private readonly ISettingsRepository _settings;
    public SettingsService(ISettingsRepository settings)
    {
        _settings = settings;

    }
    public LocalSettingsData? GetSettings()
	{
		return _settings.GetSettings();
	}

	public bool UpdateSettings(LocalSettingsData settings)
	{
		//validation can be run
		return _settings.UpdateSettings(settings);
	}
}
