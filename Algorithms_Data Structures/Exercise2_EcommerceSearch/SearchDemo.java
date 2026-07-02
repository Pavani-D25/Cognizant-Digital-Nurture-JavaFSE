public class SearchDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 2: E-commerce Platform Search ===\n");

        // Unsorted array for linear search
        Product[] products = {
            new Product(201, "Laptop Pro", "Electronics"),
            new Product(202, "Running Shoes", "Footwear"),
            new Product(203, "Coffee Maker", "Kitchen"),
            new Product(204, "Desk Lamp", "Furniture"),
            new Product(205, "Yoga Mat", "Fitness"),
            new Product(206, "Bluetooth Speaker", "Electronics"),
            new Product(207, "Backpack", "Accessories"),
            new Product(208, "Water Bottle", "Fitness")
        };

        // --- Linear Search ---
        System.out.println("--- Linear Search (Unsorted Array) ---");
        SearchAlgorithms.printProducts("Available Products:", products);

        String[] searchTerms = {"Coffee Maker", "Yoga Mat", "Headphones"};
        for (String term : searchTerms) {
            long startTime = System.nanoTime();
            Product result = SearchAlgorithms.linearSearch(products, term);
            long endTime = System.nanoTime();
            if (result != null) {
                System.out.println("Found: " + result + " (Time: " + (endTime - startTime) + " ns)");
            } else {
                System.out.println("'" + term + "' not found (Time: " + (endTime - startTime) + " ns)");
            }
        }

        // --- Binary Search ---
        System.out.println("\n--- Binary Search (Sorted Array) ---");
        Product[] sortedProducts = products.clone();
        SearchAlgorithms.sortByName(sortedProducts);
        SearchAlgorithms.printProducts("Sorted Products:", sortedProducts);

        for (String term : searchTerms) {
            long startTime = System.nanoTime();
            Product result = SearchAlgorithms.binarySearch(sortedProducts, term);
            long endTime = System.nanoTime();
            if (result != null) {
                System.out.println("Found: " + result + " (Time: " + (endTime - startTime) + " ns)");
            } else {
                System.out.println("'" + term + "' not found (Time: " + (endTime - startTime) + " ns)");
            }
        }

        // --- Complexity Analysis ---
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Linear Search:");
        System.out.println("  Best Case:    O(1) - target at first position");
        System.out.println("  Average Case: O(n) - must scan on average half the array");
        System.out.println("  Worst Case:   O(n) - target at last position or absent");
        System.out.println();
        System.out.println("Binary Search:");
        System.out.println("  Best Case:    O(1) - target at midpoint");
        System.out.println("  Average Case: O(log n) - halves search space each step");
        System.out.println("  Worst Case:   O(log n) - never checks more than log2(n) elements");
        System.out.println();
        System.out.println("Recommendation: For an e-commerce platform with frequent searches");
        System.out.println("on large catalogs, binary search on a sorted/pre-indexed structure");
        System.out.println("is preferred. Linear search works for small or unsorted datasets.");
    }
}
