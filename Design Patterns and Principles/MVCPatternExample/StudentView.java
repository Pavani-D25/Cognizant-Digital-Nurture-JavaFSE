// View class - handles the display of data
public class StudentView {
    public void displayStudentDetails(String name, int id, String grade) {
        System.out.println("=== Student Details ===");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grade: " + grade);
        System.out.println("======================");
    }

    public void displayStudentList(Student[] students) {
        System.out.println("\n=== Student List ===");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("====================");
    }
}
