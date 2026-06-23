// Controller class - handles communication between Model and View
public class StudentController {
    private Student student;
    private StudentView view;

    public StudentController(Student student, StudentView view) {
        this.student = student;
        this.view = view;
    }

    // update student data
    public void setStudentName(String name) {
        student.setName(name);
    }

    public void setStudentId(int id) {
        student.setId(id);
    }

    public void setStudentGrade(String grade) {
        student.setGrade(grade);
    }

    // get student data
    public String getStudentName() {
        return student.getName();
    }

    public int getStudentId() {
        return student.getId();
    }

    public String getStudentGrade() {
        return student.getGrade();
    }

    // update the view with current student data
    public void updateView() {
        view.displayStudentDetails(student.getName(), student.getId(), student.getGrade());
    }

    public Student getStudent() {
        return student;
    }
}
