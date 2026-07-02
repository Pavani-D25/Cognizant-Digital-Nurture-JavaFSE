import java.util.Arrays;
import java.util.Comparator;

/**
 * SearchAlgorithms provides linear and binary search implementations
 * for e-commerce product search functionality.
 * 
 * Comparison:
 * - Linear Search:  O(n) - checks each element sequentially
 * - Binary Search:  O(log n) - requires sorted array, halves search space
 * 
 * For large product catalogs, binary search is preferred when:
 * 1. Products can be pre-sorted by name
 * 2. Search frequency is high (amortized sort cost)
 * 3. Dataset is large enough to benefit from O(log n)
 */
public class SearchAlgorithms {

    /**
     * Linear Search - scans each product one by one.
     * Time Complexity: O(n) - must check every element in worst case
     * Best Case: O(1) - target is first element
     * Average Case: O(n/2) -> O(n)
     * Worst Case: O(n) - target is last or not present
     * 
     * @param products array of products to search
     * @param name     the product name to find
     * @return the matching product, or null if not found
     */
    public static Product linearSearch(Product[] products, String name) {
        if (products == null || name == null) {
            return null;
        }
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    /**
     * Binary Search - requires array sorted by productName.
     * Time Complexity: O(log n) - halves the search space each step
     * Best Case: O(1) - target is at midpoint
     * Average Case: O(log n)
     * Worst Case: O(log n)
     * 
     * Precondition: products array must be sorted by productName ascending.
     * 
     * @param sortedProducts array sorted by productName
     * @param name           the product name to find
     * @return the matching product, or null if not found
     */
    public static Product binarySearch(Product[] sortedProducts, String name) {
        if (sortedProducts == null || name == null) {
            return null;
        }
        int left = 0;
        int right = sortedProducts.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedProducts[mid].getProductName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return sortedProducts[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    /**
     * Sorts products by name for binary search.
     */
    public static void sortByName(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Prints all products in the array.
     */
    public static void printProducts(String label, Product[] products) {
        System.out.println(label);
        for (Product p : products) {
            System.out.println("  " + p);
        }
    }
}
