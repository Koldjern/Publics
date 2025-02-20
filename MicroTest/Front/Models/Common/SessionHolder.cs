using Front.Common;
using System.Xml.Linq;

namespace Front.Models.Common;

public class SessionHolder<T> where T : ISessionItem<T> 
{
	private ISession? _session { get; set; }
	
    public EventHandler<T> UpdateValue { get; }
	private Func<T> _ctr;
    public SessionHolder(IHttpContextAccessor context, Func<T> constructor)
    {
		_ctr = constructor;
		_session = context.HttpContext?.Session;
		UpdateValue += SetJson;
	}
	public T GetValue()
	{
		T val = _session == null ? _ctr() : _session.GetJson<T>() ?? _ctr();
		val.AddListener(UpdateValue);
		return val;
	}
	private void SetJson(object? sender, T t)
	{
		_session?.SetJson<T>(typeof(T).Name, t);
	}
}
