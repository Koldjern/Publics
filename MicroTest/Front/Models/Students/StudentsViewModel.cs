using Domain.Entities;
using Front.Models.Common;

namespace Front.Models.Students;

public class StudentsViewModel : PagedParentViewModel<Student>
{
	public string? Education { get; set; }
	public StudentsViewModel(IEnumerable<Student> students, PagingInfo pagingInfo, string? education)
		: base(students, pagingInfo)
	{
		Education = education;
	}
}
