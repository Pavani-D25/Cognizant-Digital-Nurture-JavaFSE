import java.util.Arrays;

public class SortingDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 3: Sorting Customer Orders ===\n");

        // Original unsorted orders
        Order[] original = {
            new Order(501, "Alice Johnson", 250.00),
            new Order(502, "Bob Smith", 125.50),
            new Order(503, "Carol White", 500.75),
            new Order(504, "David Brown", 89.99),
            new Order(505, "Eva Martinez", 320.00),
            new Order(506, "Frank Lee", 175.25),
            new Order(507, "Grace Kim", 410.50),
            new Order(508, "Henry Davis", 65.00)
        };

        // --- Bubble Sort ---
        System.out.println("--- Bubble Sort ---");
        Order[] bubbleArr = original.clone();
        SortingAlgorithms.printOrders("Before Sorting:", bubbleArr);

        long startBubble = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleArr);
        long endBubble = System.nanoTime();

        SortingAlgorithms.printOrders("After Bubble Sort (by Total Price):", bubbleArr);
        System.out.println("Bubble Sort Time: " + (endBubble - startBubble) + " ns\n");

        // --- Quick Sort ---
        System.out.println("--- Quick Sort ---");
        Order[] quickArr = original.clone();
        SortingAlgorithms.printOrders("Before Sorting:", quickArr);

        long startQuick = System.nanoTime();
        SortingAlgorithms.quickSort(quickArr);
        long endQuick = System.nanoTime();

        SortingAlgorithms.printOrders("After Quick Sort (by Total Price):", quickArr);
        System.out.println("Quick Sort Time: " + (endQuick - startQuick) + " ns\n");

        // --- Performance with larger dataset ---
        System.out.println("--- Performance Comparison (10,000 orders) ---");
        Order[] largeData = generateRandomOrders(10000);

        Order[] bubbleTest = largeData.clone();
        Order[] quickTest = largeData.clone();

        long bStart = System.nanoTime();
        SortingAlgorithms.bubbleSort(bubbleTest);
        long bEnd = System.nanoTime();

        long qStart = System.nanoTime();
        SortingAlgorithms.quickSort(quickTest);
        long qEnd = System.nanoTime();

        System.out.println("Bubble Sort on 10,000 orders: " + (bEnd - bStart) / 1_000_000.0 + " ms");
        System.out.println("Quick Sort on 10,000 orders:  " + (qEnd - qStart) / 1_000_000.0 + " ms");

        // --- Complexity Analysis ---
        System.out.println("\n=== Time Complexity Comparison ===");
        System.out.println("Algorithm     | Best Case  | Average Case | Worst Case | Space");
        System.out.println("--------------|------------|--------------|------------|-------");
        System.out.println("Bubble Sort   | O(n)       | O(n^2)       | O(n^2)     | O(1)");
        System.out.println("Quick Sort    | O(n log n) | O(n log n)   | O(n^2)     | O(log n)");
        System.out.println();
        System.out.println("Quick Sort is preferred because:");
        System.out.println("1. O(n log n) average case significantly outperforms O(n^2)");
        System.out.println("2. Better cache performance due to divide-and-conquer");
        System.out.println("3. In practice, worst case is rare with proper pivot selection");
    }

    private static Order[] generateRandomOrders(int count) {
        Order[] orders = new Order[count];
        for (int i = 0; i < count; i++) {
            orders[i] = new Order(600 + i, "Customer" + i, Math.random() * 1000);
        }
        return orders;
    }
}
