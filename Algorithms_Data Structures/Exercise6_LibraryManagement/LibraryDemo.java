public class LibraryDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 6: Library Management System ===\n");

        Book[] books = {
            new Book(401, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(402, "To Kill a Mockingbird", "Harper Lee"),
            new Book(403, "1984", "George Orwell"),
            new Book(404, "Pride and Prejudice", "Jane Austen"),
            new Book(405, "The Catcher in the Rye", "J.D. Salinger"),
            new Book(406, "Brave New World", "Aldous Huxley"),
            new Book(407, "The Hobbit", "J.R.R. Tolkien"),
            new Book(408, "Fahrenheit 451", "Ray Bradbury"),
            new Book(409, "Jane Eyre", "Charlotte Bronte"),
            new Book(410, "The Lord of the Rings", "J.R.R. Tolkien")
        };

        // --- Linear Search by Title ---
        System.out.println("--- Linear Search by Title ---");
        LibrarySearch.printBooks("Available Books:", books);

        String[] searchTitles = {"1984", "The Hobbit", "Harry Potter"};
        for (String title : searchTitles) {
            long start = System.nanoTime();
            Book result = LibrarySearch.linearSearchByTitle(books, title);
            long end = System.nanoTime();
            if (result != null) {
                System.out.println("Found: " + result + " [" + (end - start) + " ns]");
            } else {
                System.out.println("'" + title + "' not found [" + (end - start) + " ns]");
            }
        }

        // --- Linear Search by Author ---
        System.out.println("\n--- Linear Search by Author ---");
        String[] authors = {"J.R.R. Tolkien", "George Orwell", "Stephen King"};
        for (String author : authors) {
            Book[] found = LibrarySearch.linearSearchByAuthor(books, author);
            if (found.length > 0) {
                System.out.println("Books by " + author + ":");
                for (Book b : found) {
                    System.out.println("  " + b);
                }
            } else {
                System.out.println("No books found by " + author);
            }
        }

        // --- Binary Search by Title ---
        System.out.println("\n--- Binary Search by Title ---");
        Book[] sortedBooks = books.clone();
        LibrarySearch.sortByTitle(sortedBooks);
        LibrarySearch.printBooks("Sorted by Title:", sortedBooks);

        for (String title : searchTitles) {
            long start = System.nanoTime();
            Book result = LibrarySearch.binarySearchByTitle(sortedBooks, title);
            long end = System.nanoTime();
            if (result != null) {
                System.out.println("Found: " + result + " [" + (end - start) + " ns]");
            } else {
                System.out.println("'" + title + "' not found [" + (end - start) + " ns]");
            }
        }

        // --- Analysis ---
        System.out.println("\n=== Time Complexity Comparison ===");
        System.out.println("Algorithm     | Best Case | Average Case | Worst Case | Requires Sort?");
        System.out.println("--------------|-----------|--------------|------------|---------------");
        System.out.println("Linear Search | O(1)      | O(n)         | O(n)       | No");
        System.out.println("Binary Search | O(1)      | O(log n)     | O(log n)   | Yes");
        System.out.println();
        System.out.println("When to Use Each Algorithm:");
        System.out.println("Linear Search:");
        System.out.println("  - Small datasets (< 100 items)");
        System.out.println("  - Unsorted data that cannot be pre-sorted");
        System.out.println("  - Infrequent searches");
        System.out.println("  - Simple implementation preferred");
        System.out.println();
        System.out.println("Binary Search:");
        System.out.println("  - Large datasets (> 1000 items)");
        System.out.println("  - Data can be sorted once and searched many times");
        System.out.println("  - Frequent search operations");
        System.out.println("  - Performance-critical applications");
    }
}
