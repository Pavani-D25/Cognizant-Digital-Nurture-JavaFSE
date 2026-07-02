/**
 * SortingAlgorithms provides Bubble Sort and Quick Sort implementations
 * for sorting customer orders by total price.
 * 
 * Comparison:
 * - Bubble Sort:  O(n^2) - simple but inefficient for large datasets
 * - Quick Sort:   O(n log n) average - fast, divide-and-conquer approach
 * 
 * Quick Sort is preferred for production systems due to superior
 * average-case performance, despite O(n^2) worst case.
 */
public class SortingAlgorithms {

    /**
     * Bubble Sort - repeatedly swaps adjacent elements if out of order.
     * Time Complexity:
     *   Best Case:    O(n) - already sorted (with optimization flag)
     *   Average Case: O(n^2)
     *   Worst Case:   O(n^2) - reverse sorted
     * Space Complexity: O(1) - in-place sorting
     * 
     * @param orders array of Order objects to sort by totalPrice ascending
     */
    public static void bubbleSort(Order[] orders) {
        if (orders == null || orders.length <= 1) {
            return;
        }
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swaps occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Quick Sort - divide and conquer using a pivot element.
     * Time Complexity:
     *   Best Case:    O(n log n) - balanced partitions
     *   Average Case: O(n log n) - random data
     *   Worst Case:   O(n^2) - already sorted (mitigated by random pivot)
     * Space Complexity: O(log n) - recursive call stack
     * 
     * @param orders array of Order objects to sort by totalPrice ascending
     */
    public static void quickSort(Order[] orders) {
        if (orders == null || orders.length <= 1) {
            return;
        }
        quickSortHelper(orders, 0, orders.length - 1);
    }

    private static void quickSortHelper(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSortHelper(orders, low, pivotIndex - 1);
            quickSortHelper(orders, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions array around a pivot (last element).
     * Places pivot in correct sorted position.
     */
    private static int partition(Order[] orders, int low, int high) {
        double pivotValue = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivotValue) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    /**
     * Prints all orders in the array.
     */
    public static void printOrders(String label, Order[] orders) {
        System.out.println(label);
        for (Order o : orders) {
            System.out.println("  " + o);
        }
    }
}
