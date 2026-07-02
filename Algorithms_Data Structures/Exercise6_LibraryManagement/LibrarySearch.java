import java.util.Arrays;
import java.util.Comparator;

/**
 * LibrarySearch provides search algorithms for finding books by title.
 * 
 * Algorithm Selection Guidelines:
 * 
 * Use Linear Search when:
 * 1. Data is unsorted or cannot be sorted
 * 2. Dataset is small (< 100 elements)
 * 3. Searches are infrequent
 * 4. Simplicity is preferred over performance
 * 
 * Use Binary Search when:
 * 1. Data can be pre-sorted
 * 2. Dataset is large
 * 3. Searches are frequent (amortized sort cost)
 * 4. O(log n) performance is required
 */
public class LibrarySearch {

    /**
     * Linear Search - checks each book sequentially.
     * Time Complexity: O(n)
     * - Best Case: O(1) - first element matches
     * - Average Case: O(n/2)
     * - Worst Case: O(n) - last element or not found
     * 
     * @param books array of books to search
     * @param title the title to find (case-insensitive)
     * @return the matching book, or null if not found
     */
    public static Book linearSearchByTitle(Book[] books, String title) {
        if (books == null || title == null) {
            return null;
        }
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    /**
     * Linear Search - finds all books by a given author.
     * Time Complexity: O(n)
     * 
     * @param books  array of books
     * @param author the author name (case-insensitive)
     * @return array of matching books
     */
    public static Book[] linearSearchByAuthor(Book[] books, String author) {
        if (books == null || author == null) {
            return new Book[0];
        }
        // First pass: count matches
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                count++;
            }
        }
        // Second pass: collect matches
        Book[] result = new Book[count];
        int index = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result[index++] = book;
            }
        }
        return result;
    }

    /**
     * Binary Search - requires array sorted by title.
     * Time Complexity: O(log n)
     * - Best Case: O(1) - midpoint matches
     * - Average Case: O(log n)
     * - Worst Case: O(log n)
     * 
     * @param sortedBooks array sorted by title
     * @param title       the title to find
     * @return the matching book, or null if not found
     */
    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        if (sortedBooks == null || title == null) {
            return null;
        }
        int left = 0;
        int right = sortedBooks.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = sortedBooks[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return sortedBooks[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    /**
     * Sorts books by title for binary search.
     */
    public static void sortByTitle(Book[] books) {
        Arrays.sort(books, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Prints an array of books.
     */
    public static void printBooks(String label, Book[] books) {
        System.out.println(label);
        for (Book b : books) {
            System.out.println("  " + b);
        }
    }
}
