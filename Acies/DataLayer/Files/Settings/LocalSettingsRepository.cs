using Microsoft.Extensions.Logging;
using Models.Entities.Settings;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace DataLayer.Files.Settings;

public class LocalSettingsRepository : ISettingsRepository
{
    private readonly ILogger<LocalSettingsRepository> _logger;

	public LocalSettingsRepository(ILogger<LocalSettingsRepository> logger)
    {
        _logger = logger;
    }
    private readonly string filePath = Path.Combine(AppContext.BaseDirectory, "LocalData.json");
    public LocalSettingsData? GetSettings()
    {
        try
        {
            if (File.Exists(filePath))
            {
                // Read the content of the file
                string jsonContent = File.ReadAllText(filePath);
                // Deserialize the JSON content
                var result = JsonSerializer.Deserialize<LocalSettingsData>(jsonContent);
                _logger.LogInformation("LocalData.Json Read Success");
                return result;
            }
            else
                _logger.LogCritical("LocalData.Json missing");
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "LocalData.Json Read Error");
        }
        return null;
    }
    public bool UpdateSettings(LocalSettingsData settings)
    {
        try
        {
            File.WriteAllText(filePath, JsonSerializer.Serialize(settings));
			_logger.LogInformation("LocalData.Json Update Success");
			return true;
        }
        catch (Exception ex)
        {
			_logger.LogError(ex ,"LocalData.Json Update Failure");
			return false;
        }
    }
}
