using DataLayer.Pallets;
using LogicLayer.pallet;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Models.Entities;
using PresentationLayer.Models.PalletController;

namespace PresentationLayer.Controllers;
[Authorize]
public class PalletController : Controller
{
    private readonly IPalletService _palletService;
    public PalletController(IPalletService palletService)
    {
        _palletService = palletService;
    }
    public ViewResult Create()
    {
        ViewModelPallet pallet = new ViewModelPallet();
        return View(pallet);
    }

    public ViewResult Pallets()
    {
        IEnumerable<ViewModelPallet> pallets = _palletService.GetPallets().Select(pallet => new ViewModelPallet(pallet));
        ListPallets palletsList = new ListPallets { Pallets = pallets };
        return View("Pallets", palletsList);
    }
    [HttpPost]
    public IActionResult Create(ViewModelPallet pallet)
    {
        if (!ModelState.IsValid)
            return View("Create", pallet);
        bool success = _palletService.AddPallet(pallet);
        if (!success)
        {
            //maybe add validation on model properties
            ModelState.AddModelError(string.Empty, "Unable to save the pallet. Please try again.");
            return View("Create", pallet);
        }
        return Pallets();
    }


    public IActionResult Edit(Guid id)
    {
        Pallet? pallet = _palletService.GetPallet(id);
        if (pallet == null)
            return Pallets();
        return View(pallet);
    }
    [HttpPost]

    public IActionResult Edit(ViewModelPallet pallet)
    {
        if (!ModelState.IsValid)
        return View();
        bool update = _palletService.UpdatePallet(pallet);
       
        return Pallets();
    }

    public IActionResult Profile(Guid id)
    {
        Pallet? pallet = _palletService.GetPallet(id);
        if (pallet == null)
            return Pallets();
        return View(pallet);
    }


    public IActionResult Delete(Guid id)
    {
        
        bool delete = _palletService.DeletePallet(id);
        //maybe add success/failure
       
        return Pallets();
    }

}