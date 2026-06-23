public class MVCTest {
    public static void main(String[] args) {
        System.out.println("=== MVC Pattern Test ===\n");

        // create model, view, and controller
        Student student = new Student("John Doe", 101, "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        // display initial student details
        System.out.println("--- Initial Student Details ---");
        controller.updateView();

        // update student through controller
        System.out.println("\n--- Updating Student Details ---");
        controller.setStudentName("Jane Smith");
        controller.setStudentId(102);
        controller.setStudentGrade("A+");

        // display updated details
        System.out.println("\n--- Updated Student Details ---");
        controller.updateView();

        // access student data through controller
        System.out.println("\n--- Accessing Student via Controller ---");
        System.out.println("Name: " + controller.getStudentName());
        System.out.println("ID: " + controller.getStudentId());
        System.out.println("Grade: " + controller.getStudentGrade());

        // test with multiple students
        System.out.println("\n--- Creating multiple students ---");
        Student[] students = {
            new Student("Alice Johnson", 201, "B+"),
            new Student("Bob Williams", 202, "A-"),
            new Student("Charlie Brown", 203, "B")
        };
        view.displayStudentList(students);
    }
}
