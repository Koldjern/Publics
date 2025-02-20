
using Domain.Entities;
using System.ComponentModel;

namespace Front.Models.Common;

public class Cart : ISessionItem<Cart>
{
	public ObservableList<CartLine> Lines { get; set; } = new ObservableList<CartLine>();
	private DisposableEventHandler<Cart> _stateChanged = new ();
    public void AddItem(Product product, int quantity)
	{
		CartLine? line = Lines
		.Where(p => p.Product.ProductID == product.ProductID)
		.FirstOrDefault();
		if (line == null)
		{
			Lines.Add(new CartLine
			{
				Product = product,
				Quantity = quantity
			});
		}
		else
		{
			line.Quantity += quantity;
		
			_stateChanged.Invoke(this, this);
		}

	}
	public void RemoveLine(Product product) =>
	Lines.RemoveAll(l => l.Product.ProductID == product.ProductID);
	public decimal ComputeTotalValue() =>
	Lines.Sum(e => e.Product.Price * e.Quantity);
	public void Clear() => Lines.Clear();

	public void AddListener(EventHandler<Cart> handler)
	{
		_stateChanged.AddSubscriber(handler);
		Lines.ListChanged.AddSubscriber((sender, e) => { _stateChanged.Invoke(this, this); });
	}

}
