// Service class - depends on CustomerRepository (injected via constructor)
public class CustomerService {
    private CustomerRepository customerRepository;

    // constructor injection - dependency is passed in, not created here
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomerDetails(int id) {
        System.out.println("CustomerService: Fetching customer details for ID " + id);
        return customerRepository.findCustomerById(id);
    }

    public String getAllCustomers() {
        System.out.println("CustomerService: Fetching all customers");
        return customerRepository.findAllCustomers();
    }

    public boolean addCustomer(String name, int id) {
        System.out.println("CustomerService: Adding customer " + name + " with ID " + id);
        return customerRepository.saveCustomer(name, id);
    }
}
