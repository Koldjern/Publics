using AppSport.Persistence.Repository;
using AppStudent.Persistence.Repository;
using Microsoft.AspNetCore.Mvc;

namespace Front.Common.Components;

public class StudentNavMenuViewComponent : ViewComponent
{
    private readonly IStudentRepository _studentRepository;
    public StudentNavMenuViewComponent(IStudentRepository studentRepository)
    {
		_studentRepository = studentRepository;
    }
    public async Task<IViewComponentResult> InvokeAsync()
    {
		return View(await _studentRepository.GetEducations());
    }
}
