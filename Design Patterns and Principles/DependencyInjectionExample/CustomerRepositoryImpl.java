import java.util.HashMap;
import java.util.Map;

// Concrete repository implementation - simulates database with HashMap
public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<Integer, String> customerDatabase;

    public CustomerRepositoryImpl() {
        customerDatabase = new HashMap<>();
        // pre-populate with some sample data
        customerDatabase.put(1, "John Smith");
        customerDatabase.put(2, "Jane Doe");
        customerDatabase.put(3, "Bob Johnson");
    }

    @Override
    public String findCustomerById(int id) {
        return customerDatabase.getOrDefault(id, "Customer not found with ID: " + id);
    }

    @Override
    public String findAllCustomers() {
        StringBuilder sb = new StringBuilder("All Customers:\n");
        for (Map.Entry<Integer, String> entry : customerDatabase.entrySet()) {
            sb.append("ID: ").append(entry.getKey()).append(" - Name: ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean saveCustomer(String name, int id) {
        customerDatabase.put(id, name);
        return true;
    }
}
