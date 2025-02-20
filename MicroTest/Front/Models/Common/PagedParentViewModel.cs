using Domain.Entities;

namespace Front.Models.Common;

public class PagedParentViewModel<T>
{
	public IEnumerable<T> Values { get; set; }
	= Enumerable.Empty<T>();
	public PagingInfo PagingInfo { get; set; } = new();
    public PagedParentViewModel()
    {
        
    }
    public PagedParentViewModel(IEnumerable<T> values, PagingInfo pagingInfo)
    {
        Values = values;
        PagingInfo = pagingInfo;
    }
}
