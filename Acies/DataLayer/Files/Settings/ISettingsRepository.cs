using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Models.Entities.Settings;
namespace DataLayer.Files.Settings;
public interface ISettingsRepository
{
    LocalSettingsData? GetSettings();
    bool UpdateSettings(LocalSettingsData settings);
}
