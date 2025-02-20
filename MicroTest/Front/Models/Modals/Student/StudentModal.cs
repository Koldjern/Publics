using Front.Models.Students;

namespace Front.Models.Modals.Student;

public class StudentModal
{
    public StudentViewModel Student { get; set; } = new StudentViewModel();
    public string TaskDescription { get; set; } = "Create";
    public string ActionString { get; set; } = "CreateStudent";
    public bool Validate { get; set; } = false;
}
