using System.ComponentModel;

namespace Front.Models.Common
{
	public class ObservableList<T> : List<T> 
	{
		public DisposableEventHandler<T> ListChanged = new();
        public ObservableList()
        {
            AddRange(new List<T>());
        }
        public void OnListChanged(object? sender, T e)
		{
			ListChanged?.Invoke(this, e);
		}

		private void OnListChanged()
		{
			OnListChanged(this, default);
		}
		public new void Add(T item)
		{
			base.Add(item);
			OnListChanged();  
		}
		public new void AddRange(IEnumerable<T> items)
		{
            base.AddRange(items);
			OnListChanged();
        }
		public new bool Remove(T item)
		{
			bool result = base.Remove(item);
			if (result)
			{
				OnListChanged();  
			}
			return result;
		}

		public new void Clear()
		{
			base.Clear();
			OnListChanged();  
		}

	}
}
