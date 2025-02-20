using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Models.Common;
using Models.Entities;
namespace PresentationLayer.Models.PalletController;

public class ViewModelPallet : Pallet
{
    public ViewModelPallet()
    {
        
    }
    public ViewModelPallet(Pallet pallet) : base(pallet)
    {
        
    }
    public override Guid Id { get; set; } = Guid.NewGuid();
    [Required(ErrorMessage = "skriv noget her forhelved!!!!!")]
    [StringLength(200, ErrorMessage = "beskrivelsen skal værre under 200 ord")]
    public override string? Description { get; set; } = "";
    [Required(ErrorMessage = "du skal have mål på")]
    public override Dimensions3D Dimensions { get; set; } = new Dimensions3D();
    public override string? PalletGroup { get; set; } = "";
    [Required(ErrorMessage = "hva er pallen lavet af")]
    public override string? PalletMaterial { get; set; } = "";
    [Range(1, 1000, ErrorMessage = "vægten skal være imellem 1-1000kg")]
    public override int Weight { get; set; } = 0;
    [Range(1, 3000, ErrorMessage = "max højde skal værre imellem 1-3000mm.")]
    public override int MaxHeight { get; set; } = 0;
    [Range(1, 4000, ErrorMessage = "Max vægt på pallen skal være imellem 1 and 4000kg")]
    public override int MaxWeight { get; set; } = 0;
    public override int OverReach { get; set; } = 0;
    public override int Spots { get; set; } = 0;
    public override bool SpecialCargo { get; set; } 
    public override int SpaceBetween { get; set; } = 0;
    public override bool InUse { get; set; }


    //validation can be added on properties
}