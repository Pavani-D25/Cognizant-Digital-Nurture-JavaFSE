import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * InventoryManager uses a HashMap to store products keyed by productId.
 * 
 * Time Complexity Analysis:
 * - addProduct:    O(1) average - HashMap insertion
 * - updateProduct: O(1) average - HashMap lookup and update
 * - deleteProduct: O(1) average - HashMap removal
 * - getProduct:    O(1) average - HashMap retrieval
 * - getAllProducts: O(n) - must iterate all entries
 * 
 * HashMap is chosen because:
 * 1. Fast O(1) average-case operations for add/update/delete
 * 2. Key-based access via productId is natural for inventory lookups
 * 3. Dynamic sizing avoids fixed-capacity issues of arrays
 */
public class InventoryManager {
    private HashMap<Integer, Product> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    /**
     * Adds a new product to the inventory.
     * Time Complexity: O(1) average - direct HashMap put operation
     * 
     * @param product the product to add
     * @return true if added successfully, false if product ID already exists
     */
    public boolean addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product with ID " + product.getProductId() + " already exists.");
            return false;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product);
        return true;
    }

    /**
     * Updates an existing product's details.
     * Time Complexity: O(1) average - HashMap containsKey + put
     * 
     * @param productId      the ID of the product to update
     * @param newProductName new name (null to keep current)
     * @param newQuantity    new quantity (-1 to keep current)
     * @param newPrice       new price (-1 to keep current)
     * @return true if updated, false if product not found
     */
    public boolean updateProduct(int productId, String newProductName, int newQuantity, double newPrice) {
        if (!inventory.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " not found.");
            return false;
        }
        Product p = inventory.get(productId);
        if (newProductName != null) {
            p.setProductName(newProductName);
        }
        if (newQuantity >= 0) {
            p.setQuantity(newQuantity);
        }
        if (newPrice >= 0) {
            p.setPrice(newPrice);
        }
        System.out.println("Updated: " + p);
        return true;
    }

    /**
     * Removes a product from the inventory.
     * Time Complexity: O(1) average - HashMap remove operation
     * 
     * @param productId the ID of the product to remove
     * @return the removed product, or null if not found
     */
    public Product deleteProduct(int productId) {
        Product removed = inventory.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed);
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
        return removed;
    }

    /**
     * Retrieves a product by ID.
     * Time Complexity: O(1) average - HashMap get operation
     */
    public Product getProduct(int productId) {
        return inventory.get(productId);
    }

    /**
     * Returns all products in the inventory.
     * Time Complexity: O(n) - iterates through all entries
     */
    public Collection<Product> getAllProducts() {
        return inventory.values();
    }

    /**
     * Returns the total number of products.
     */
    public int getSize() {
        return inventory.size();
    }

    /**
     * Displays all products in the inventory.
     */
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory (" + inventory.size() + " products) ---");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
        System.out.println("--------------------------------------");
    }
}
