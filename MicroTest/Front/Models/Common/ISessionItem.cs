namespace Front.Models.Common;

public interface ISessionItem<T>
{
	void AddListener(EventHandler<T> handler);
}
