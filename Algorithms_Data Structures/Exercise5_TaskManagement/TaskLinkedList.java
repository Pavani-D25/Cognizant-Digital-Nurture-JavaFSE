/**
 * TaskLinkedList - A singly linked list implementation for task management.
 * 
 * Linked List Types:
 * - Singly Linked List: Each node points to the next node only.
 *   - Simple and memory efficient
 *   - Can only traverse forward
 * - Doubly Linked List: Each node points to both next and previous nodes.
 *   - Allows bidirectional traversal
 *   - Extra memory for previous pointer
 * 
 * Advantages over Arrays:
 * 1. Dynamic size - grows/shrinks as needed without reallocation
 * 2. Efficient insertion/deletion at any position - O(1) if position known
 * 3. No memory waste - allocates exactly what's needed
 * 
 * Disadvantages:
 * 1. No random access - must traverse from head to reach element
 * 2. Extra memory for pointers in each node
 * 3. Poor cache performance due to non-contiguous memory
 */
public class TaskLinkedList {

    /**
     * Inner class representing a node in the linked list.
     */
    private static class Node {
        Task data;
        Node next;

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Adds a task to the end of the list.
     * Time Complexity: O(n) - must traverse to end
     * (Can be O(1) with a tail pointer)
     * 
     * @param task the task to add
     */
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Added: " + task);
    }

    /**
     * Adds a task at a specific position.
     * Time Complexity: O(n) - must traverse to position
     * 
     * @param index  the position to insert at (0-based)
     * @param task   the task to insert
     */
    public void addTaskAt(int index, Task task) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index: " + index);
            return;
        }
        Node newNode = new Node(task);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
        System.out.println("Added at position " + index + ": " + task);
    }

    /**
     * Searches for a task by ID.
     * Time Complexity: O(n) - must check each node
     * 
     * @param taskId the ID to search for
     * @return the matching task, or null if not found
     */
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.data.getTaskId() == taskId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Deletes a task by ID.
     * Time Complexity: O(n) - must find the node and adjust pointers
     * 
     * @param taskId the ID of the task to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return false;
        }
        if (head.data.getTaskId() == taskId) {
            Task removed = head.data;
            head = head.next;
            size--;
            System.out.println("Deleted: " + removed);
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getTaskId() == taskId) {
                Task removed = current.next.data;
                current.next = current.next.next;
                size--;
                System.out.println("Deleted: " + removed);
                return true;
            }
            current = current.next;
        }
        System.out.println("Task with ID " + taskId + " not found.");
        return false;
    }

    /**
     * Traverses and displays all tasks.
     * Time Complexity: O(n) - visits each node once
     */
    public void traverse() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        System.out.println("\n--- Task List (" + size + " tasks) ---");
        Node current = head;
        int index = 0;
        while (current != null) {
            System.out.println("[" + index + "] " + current.data);
            current = current.next;
            index++;
        }
        System.out.println("--------------------------");
    }

    /**
     * Returns the number of tasks.
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the list is empty.
     */
    public boolean isEmpty() {
        return head == null;
    }
}
