public class InventoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Inventory Management System ===\n");

        InventoryManager manager = new InventoryManager();

        // Adding products
        System.out.println("--- Adding Products ---");
        manager.addProduct(new Product(101, "Wireless Mouse", 150, 29.99));
        manager.addProduct(new Product(102, "Mechanical Keyboard", 75, 89.50));
        manager.addProduct(new Product(103, "USB-C Hub", 200, 45.00));
        manager.addProduct(new Product(104, "Monitor Stand", 50, 35.75));
        manager.addProduct(new Product(105, "Webcam HD", 120, 64.99));

        manager.displayInventory();

        // Attempting to add a duplicate product
        System.out.println("\n--- Adding Duplicate Product ---");
        manager.addProduct(new Product(101, "Gaming Mouse", 50, 49.99));

        // Updating a product
        System.out.println("\n--- Updating Product ---");
        manager.updateProduct(102, "RGB Mechanical Keyboard", -1, 99.99);
        manager.updateProduct(103, null, 180, -1);

        manager.displayInventory();

        // Deleting a product
        System.out.println("\n--- Deleting Product ---");
        manager.deleteProduct(104);

        // Attempting to delete a non-existent product
        manager.deleteProduct(999);

        manager.displayInventory();

        // Searching for a product
        System.out.println("\n--- Searching Product by ID ---");
        Product found = manager.getProduct(103);
        if (found != null) {
            System.out.println("Found: " + found);
        }

        System.out.println("\nTotal products in inventory: " + manager.getSize());

        System.out.println("\n=== Time Complexity Summary ===");
        System.out.println("addProduct:    O(1) average - HashMap insertion");
        System.out.println("updateProduct: O(1) average - HashMap lookup + update");
        System.out.println("deleteProduct: O(1) average - HashMap removal");
        System.out.println("getProduct:    O(1) average - HashMap retrieval");
        System.out.println("getAllProducts: O(n) - full iteration");
    }
}
