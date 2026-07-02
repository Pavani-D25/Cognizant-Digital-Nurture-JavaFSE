/**
 * EmployeeArrayManager uses a fixed-size array to store employee records.
 * 
 * Array Representation in Memory:
 * - Arrays are stored as contiguous blocks of memory
 * - Each element is accessed via index (base address + index * element size)
 * - This enables O(1) random access
 * 
 * Advantages of Arrays:
 * 1. O(1) access time by index
 * 2. Memory efficient (no overhead for pointers/references)
 * 3. Cache-friendly (contiguous memory layout)
 * 
 * Limitations:
 * 1. Fixed size - cannot grow dynamically
 * 2. Insertion/deletion is expensive (O(n) for shifting)
 * 3. Waste memory if not fully utilized
 */
public class EmployeeArrayManager {
    private Employee[] employees;
    private int count;
    private static final int DEFAULT_CAPACITY = 50;

    public EmployeeArrayManager() {
        this.employees = new Employee[DEFAULT_CAPACITY];
        this.count = 0;
    }

    public EmployeeArrayManager(int capacity) {
        this.employees = new Employee[capacity];
        this.count = 0;
    }

    /**
     * Adds an employee to the array.
     * Time Complexity: O(1) if space available - direct index assignment
     * 
     * @param employee the employee to add
     * @return true if added, false if array is full
     */
    public boolean addEmployee(Employee employee) {
        if (count >= employees.length) {
            System.out.println("Array is full. Cannot add more employees.");
            return false;
        }
        employees[count] = employee;
        count++;
        System.out.println("Added: " + employee);
        return true;
    }

    /**
     * Searches for an employee by ID using linear scan.
     * Time Complexity: O(n) - must check each element
     * 
     * @param employeeId the ID to search for
     * @return the matching employee, or null if not found
     */
    public Employee searchById(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    /**
     * Deletes an employee by ID.
     * Time Complexity: O(n) - search O(n) + shift O(n)
     * 
     * @param employeeId the ID of the employee to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return false;
        }
        Employee removed = employees[index];
        // Shift elements to fill the gap
        for (int i = index; i < count - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[count - 1] = null;
        count--;
        System.out.println("Deleted: " + removed);
        return true;
    }

    /**
     * Traverses and displays all employees.
     * Time Complexity: O(n) - must visit each element
     */
    public void traverse() {
        if (count == 0) {
            System.out.println("No employees in the array.");
            return;
        }
        System.out.println("\n--- Employee Records (" + count + "/" + employees.length + ") ---");
        for (int i = 0; i < count; i++) {
            System.out.println("[" + i + "] " + employees[i]);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Returns the current number of employees.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the capacity of the array.
     */
    public int getCapacity() {
        return employees.length;
    }
}
