using Microsoft.AspNetCore.Mvc;
using Models.Entities;
using LogicLayer.Orders;
using LogicLayer.pallet;
using PresentationLayer.Models.OrderController;
using PresentationLayer.Models.Session;
using PresentationLayer.Models;
using Models.Common;
using LogicLayer.Packing;
using Microsoft.AspNetCore.Authorization;

namespace PresentationLayer.Controllers;
[Authorize]
//Cheeky constructor
public class OrderController(IOrderService orderService, IPalletService palletService,
	SessionData session, IPackingService packingService) : Controller
{
	private readonly IOrderService _orderService = orderService;
	private readonly IPalletService _palletService = palletService;
	private readonly SessionData _session = session;
	private readonly IPackingService _packingService = packingService;
	[HttpGet] //tror ikke denne bliver brugt
	public IActionResult Details(int? id)
	{
		// Validere Id
		if (id == null)
		{
			return BadRequest("Order ID is required");
		}

		// Fetch order fra Id
		var order = _orderService.GetOrder((int)id);

		if (order == null)
		{
			return NotFound("Order not found");
		}

		// Sortering af Elementer i grupper baseret på Id, OBS: Skal nok ændres til andet end Id
		var groupedOrders = order.Elements
			.GroupBy(e => e.Id)
			.Select(static group => new
			{
				Delivery = $"Group {group.Key}",
				Elements = group.Select(e => new { e.Id, e.Quantity }).ToList()
			}).ToList();


		ViewBag.GroupedOrders = groupedOrders;

		return View("Details", order);
	}

	public IActionResult List(int id)
	{
		IEnumerable<Order>? orders = _orderService.GetOrders();

		if (orders == null)
		{
			return NotFound("Order not found");
		}
		else
		{
			ViewBag.activeOrderId = id;

			return View("List", orders);
		}
	}
	public IActionResult Pallets(int id)
	{
		Order? order = _orderService.GetOrder(id);
		if (order == null)
			return NotFound("Order not found");
		IEnumerable<Pallet> pallets = _palletService.GetPallets();

		return View("Pallets", new PalletsSelectionViewModel() { OrderId = id, Pallets = pallets });
	}
	[HttpPost]
	public IActionResult Pallets(PalletsSelectionViewModel model)
	{
		if (!ModelState.IsValid)
		{
			model.Pallets = _palletService.GetPallets();
			return View("Pallets", model);
		}

		return ElementsEdit(model);
	}


	public IActionResult ElementsEdit(PalletsSelectionViewModel input)
	{
		Order? order = _orderService.GetOrder(input.OrderId);
		if (order == null)
			return NotFound("Order not found");

		List<Pallet?> pallets = input.SelectedIds.Select(x => _palletService.GetPallet(x)).ToList();
		pallets.RemoveAll(x => x == null);

		_session.SetPallets(input.SelectedIds);

		PlacedOrderViewModel placedOrder = new PlacedOrderViewModel(order);
		OrderEditViewModel model = new OrderEditViewModel(pallets.AsEnumerable()!, placedOrder, input.OrderId);

		return View("ElementsEdit", model);
	}

	[HttpPost]
	public IActionResult ElementsEdit(OrderEditViewModel model)
	{
		//get original values back again, and set order in session
		var pallets = _session.GetPallets();
		var originalOrder = _orderService.GetOrder(model.OrderId);
		var result = UniteElements(originalOrder, model.PlacedOrder);
		if (originalOrder == null)
			return NotFound("Order not found");
		if (pallets == null)
			return NotFound("Pallets not found");
		if (result == null)
			return NotFound("Calculation error");
		_session.SetOrder(result);

		return RedirectToAction("Result");
	}

	private PlacedOrder? UniteElements(Order og, PlacedOrderViewModel model)
	{
		PlacedOrder result = new PlacedOrder(og);
		result.Elements = result.Elements.Select(x => CopyElement(x, model.Elements)).ToList()!;
		for (int i = 0; i < result.Groups.Count; i++)
		{
			result.Groups[i].Elements = result.Groups[i].Elements.Select(x => CopyElement(x, model.Groups[i].Elements)).ToList()!;
		}
		return result;
	}
	private PlacedElement? CopyElement(PlacedElement element, List<PlacedElementViewModel> elements)
	{
		var found = elements.First(x => x.Id == element.Id);
		if (found == null)
			return null;
		if (found.PalletId != Guid.Empty)
			element.Pallet = _palletService.GetPallet(found.PalletId);
		element.SpecialPallet = found.SpecialPallet;
		element.Turnable = found.Turnable;
		element.Stackable = found.Stackable;
		element.MaxSpotsOnPallet = found.MaxSpotsOnPallet;
		return element;
	}

	public IActionResult Result()
	{
		var order = _session.GetOrder();
		var pallets = _session.GetPallets();
		if (order == null)
			return NotFound("Ordre ikke valgt");
		if (pallets == null)
			return NotFound("Paller ikke valgt");
		CalculationRequest request = new CalculationRequest(order, pallets.ToArray());
		var result = _packingService.PackPallets(request);

		return View("Result", new ResultViewModel(result));
	}

}


