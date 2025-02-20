using Microsoft.AspNetCore.Mvc.ModelBinding.Validation;
using Models.Entities;
using System.ComponentModel.DataAnnotations;

namespace PresentationLayer.Models.OrderController;

public class PalletsSelectionViewModel
{
	[Required]
	public int OrderId { get; set; }
	[ValidateNever]
	public IEnumerable<Pallet> Pallets { get; set; }
	[MinLength(1, ErrorMessage ="Minimum 1 palle")]
	public List<Guid> SelectedIds { get; set; } = new List<Guid>();
}
