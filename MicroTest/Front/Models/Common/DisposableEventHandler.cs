namespace Front.Models.Common;

public class DisposableEventHandler<T> : IDisposable
{
	private List<EventHandler<T>> _subscribes = new List<EventHandler<T>>();
	private event EventHandler<T>? _handler;
	public delegate void Invoker(object? sender, T t);
	public void AddSubscriber(EventHandler<T> eventHandler)
	{
		_subscribes.Add(eventHandler);
		_handler += eventHandler;
	}
	public void Invoke(object? sender, T subject)
	{
		_handler?.Invoke(sender, subject);
	}
	public void Dispose()
	{
		_subscribes.ForEach(x => _handler -= x);
	}
}
