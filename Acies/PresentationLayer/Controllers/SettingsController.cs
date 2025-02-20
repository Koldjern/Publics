using LogicLayer.Settings;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Models.Entities.Settings;
using PresentationLayer.Models;

namespace PresentationLayer.Controllers;
[Authorize]
public class SettingsController : Controller
{
	private readonly ISettingsService _settingsService;

	public SettingsController(ISettingsService settingsService)
	{
		_settingsService = settingsService;
	}

	public IActionResult Edit()
	{
		LocalSettingsData? settings = _settingsService.GetSettings();
		if(settings != null)
		{
			SettingsModel model = new SettingsModel(settings);
			return View(model);
		}
		//add index instead
		else return RedirectToAction("Index", "Home");
	}
	[HttpPost]
	public IActionResult Edit(SettingsModel model)
	{
		if (ModelState.IsValid)
		{
			if (_settingsService.UpdateSettings(model.ToData()))
				return RedirectToAction("Index", "Home");
			//error
			return View();
		}
		//wrong val
		return View();
	}
}
