public class DependencyInjectionTest {
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Test ===\n");

        // create repository and inject it into service (dependency injection)
        System.out.println("--- Creating Repository and injecting into Service ---");
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        // test finding customers
        System.out.println("\n--- Finding customer by ID ---");
        System.out.println(service.getCustomerDetails(1));
        System.out.println(service.getCustomerDetails(2));

        // test with non-existent customer
        System.out.println("\n--- Finding non-existent customer ---");
        System.out.println(service.getCustomerDetails(99));

        // test adding new customer
        System.out.println("\n--- Adding new customer ---");
        service.addCustomer("Alice Williams", 4);

        // verify new customer was added
        System.out.println("\n--- Finding newly added customer ---");
        System.out.println(service.getCustomerDetails(4));

        // list all customers
        System.out.println("\n--- Listing all customers ---");
        System.out.println(service.getAllCustomers());
    }
}
