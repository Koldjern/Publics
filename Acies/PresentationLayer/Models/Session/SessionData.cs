using LogicLayer.pallet;
using Models.Entities;
using System.Text.Json.Serialization;

namespace PresentationLayer.Models.Session;

public class SessionData
{
    private readonly ISession? _session;
    private readonly IPalletService _palletService;

    public SessionData(IHttpContextAccessor context, IPalletService palletService)
    {
        _palletService = palletService;
        _session = context.HttpContext?.Session;
    }
    public void SetPallets(List<Guid> palletIds)
    {
        List<Pallet?> pallets = palletIds.Select(_palletService.GetPallet).ToList();
        pallets.RemoveAll(p => p == null);
        _session?.SetJson("Pallets", pallets);
    }
    public List<Pallet>? GetPallets()
    {
        return _session?.GetJson<List<Pallet>?>("Pallets");
    }
    public void SetOrder(PlacedOrder order)
    {
        _session?.SetJson("Order", order);
    }
    public PlacedOrder? GetOrder()
    {
        return _session?.GetJson<PlacedOrder?>("Order");
    }
}
