// Repository interface - defines data access methods
public interface CustomerRepository {
    String findCustomerById(int id);
    String findAllCustomers();
    boolean saveCustomer(String name, int id);
}
