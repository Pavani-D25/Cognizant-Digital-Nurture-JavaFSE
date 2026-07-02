public class TaskDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 5: Task Management System ===\n");

        TaskLinkedList taskList = new TaskLinkedList();

        // Adding tasks
        System.out.println("--- Adding Tasks ---");
        taskList.addTask(new Task(301, "Design database schema", "Pending"));
        taskList.addTask(new Task(302, "Implement user authentication", "In Progress"));
        taskList.addTask(new Task(303, "Write API endpoints", "Pending"));
        taskList.addTask(new Task(304, "Create frontend components", "Completed"));
        taskList.addTask(new Task(305, "Set up CI/CD pipeline", "In Progress"));

        taskList.traverse();

        // Adding task at specific position
        System.out.println("\n--- Adding Task at Position 2 ---");
        taskList.addTaskAt(2, new Task(306, "Code review", "Pending"));
        taskList.traverse();

        // Searching for tasks
        System.out.println("\n--- Searching Tasks ---");
        int[] searchIds = {303, 305, 999};
        for (int id : searchIds) {
            Task found = taskList.searchTask(id);
            if (found != null) {
                System.out.println("Found: " + found);
            } else {
                System.out.println("Task with ID " + id + " not found.");
            }
        }

        // Deleting tasks
        System.out.println("\n--- Deleting Tasks ---");
        taskList.deleteTask(301);
        taskList.traverse();

        taskList.deleteTask(999);

        // Delete head task
        System.out.println("\n--- Deleting First Task ---");
        taskList.deleteTask(302);
        taskList.traverse();

        System.out.println("\nFinal task count: " + taskList.getSize());

        // --- Analysis ---
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Operation      | Complexity | Notes");
        System.out.println("---------------|------------|----------------------------------------");
        System.out.println("addTask (end)  | O(n)       | Traverse to last node");
        System.out.println("addTask (pos)  | O(n)       | Traverse to position");
        System.out.println("searchTask     | O(n)       | Linear scan from head");
        System.out.println("deleteTask     | O(n)       | Find node + adjust pointers");
        System.out.println("traverse       | O(n)       | Visit each node once");
        System.out.println();
        System.out.println("=== Linked List vs Array ===");
        System.out.println("Feature            | Linked List       | Array");
        System.out.println("-------------------|-------------------|------------------");
        System.out.println("Size               | Dynamic           | Fixed");
        System.out.println("Insert (at pos)    | O(1) if known     | O(n) shift");
        System.out.println("Delete (at pos)    | O(1) if known     | O(n) shift");
        System.out.println("Access by index    | O(n) traverse     | O(1) direct");
        System.out.println("Memory             | Per-node overhead | Contiguous block");
        System.out.println("Cache performance  | Poor              | Excellent");
    }
}
