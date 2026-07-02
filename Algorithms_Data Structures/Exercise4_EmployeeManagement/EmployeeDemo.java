public class EmployeeDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 4: Employee Management System ===\n");

        EmployeeArrayManager manager = new EmployeeArrayManager(10);

        // Adding employees
        System.out.println("--- Adding Employees ---");
        manager.addEmployee(new Employee(1001, "Sarah Connor", "Software Engineer", 85000));
        manager.addEmployee(new Employee(1002, "John Matrix", "Project Manager", 92000));
        manager.addEmployee(new Employee(1003, "Linda Hamilton", "UX Designer", 78000));
        manager.addEmployee(new Employee(1004, "Mike Reeves", "DevOps Engineer", 88000));
        manager.addEmployee(new Employee(1005, "Nina Williams", "Data Analyst", 72000));

        manager.traverse();

        // Searching for an employee
        System.out.println("\n--- Searching by ID ---");
        int searchId = 1003;
        Employee found = manager.searchById(searchId);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Employee with ID " + searchId + " not found.");
        }

        // Search for non-existent employee
        found = manager.searchById(9999);
        if (found == null) {
            System.out.println("Employee with ID 9999 not found.");
        }

        // Deleting an employee
        System.out.println("\n--- Deleting Employee ---");
        manager.deleteEmployee(1002);
        manager.traverse();

        // Attempting to delete non-existent employee
        manager.deleteEmployee(9999);

        // Adding to fill capacity
        System.out.println("\n--- Filling Array Capacity ---");
        manager.addEmployee(new Employee(1006, "Carlos Rivera", "Backend Developer", 82000));
        manager.addEmployee(new Employee(1007, "Priya Patel", "Frontend Developer", 79000));
        manager.addEmployee(new Employee(1008, "Tom Wilson", "QA Engineer", 71000));
        manager.addEmployee(new Employee(1009, "Amy Zhang", "Tech Lead", 105000));
        manager.addEmployee(new Employee(1010, "Dan Murphy", "Architect", 115000));

        manager.traverse();

        // Try adding when full
        System.out.println("\n--- Attempting to Add When Full ---");
        manager.addEmployee(new Employee(1011, "Extra Person", "Intern", 40000));

        // --- Analysis ---
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Operation      | Complexity | Notes");
        System.out.println("---------------|------------|----------------------------------");
        System.out.println("addEmployee    | O(1)       | Direct index insertion at end");
        System.out.println("searchById     | O(n)       | Linear scan through array");
        System.out.println("deleteEmployee | O(n)       | Search O(n) + shift elements O(n)");
        System.out.println("traverse       | O(n)       | Visit each element once");
        System.out.println();
        System.out.println("=== Array Limitations ===");
        System.out.println("1. Fixed size: Cannot grow beyond initial capacity");
        System.out.println("2. Deletion is expensive: Must shift all elements after the gap");
        System.out.println("3. Memory waste: Unused slots still consume memory");
        System.out.println("4. Insertion at arbitrary position: O(n) due to shifting");
        System.out.println();
        System.out.println("When to use arrays:");
        System.out.println("- When size is known and fixed");
        System.out.println("- When frequent random access is needed");
        System.out.println("- When memory efficiency is critical");
    }
}
