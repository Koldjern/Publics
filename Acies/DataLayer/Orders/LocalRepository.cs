using DataLayer.Files.TypeReaders;
using Models.Entities;

namespace DataLayer.Orders;

public class LocalRepository : IOrderRepository
{
    private ITypeHandler _reader;
	private readonly string filePath = Path.Combine(AppContext.BaseDirectory, "XMLfileorder.xml");

	public LocalRepository(ITypeHandler reader)
    {
        _reader = reader;
    }

    public IEnumerable<Order>? GetOrders()
    {
        //string rootPath = AppDomain.CurrentDomain.BaseDirectory;
        //string rootPath1 = "C:\\Users\\jakob\\Source\\Repos\\S3AciesPaller\\Acies\\PresentationLayer\\";
        string content = File.ReadAllText(filePath);
        return _reader.Read<List<Order>>(content);
    }

    public Order? GetOrder(int id)
    {
        return GetOrders()?.FirstOrDefault(x => x.Id == id);
    }
}
// C:\Users\jakob\Source\Repos\S3AciesPaller\Acies\PresentationLayer\XMLfileorder.xml
