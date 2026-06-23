public class AdapterTest {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Test ===\n");

        // create the third-party gateway objects
        StripeGateway stripeGateway = new StripeGateway();
        PayPalGateway payPalGateway = new PayPalGateway();

        // wrap them with adapters to use our PaymentProcessor interface
        PaymentProcessor stripeProcessor = new StripeAdapter(stripeGateway, "4111-1111-1111-1234");
        PaymentProcessor paypalProcessor = new PayPalAdapter(payPalGateway, "user@email.com");

        // now we can use both gateways through the same interface
        System.out.println("--- Stripe Payment ---");
        stripeProcessor.processPayment(99.99);
        System.out.println("Status: " + stripeProcessor.getTransactionStatus());
        stripeProcessor.refundPayment(99.99);

        System.out.println("\n--- PayPal Payment ---");
        paypalProcessor.processPayment(149.50);
        System.out.println("Status: " + paypalProcessor.getTransactionStatus());
        paypalProcessor.refundPayment(149.50);
    }
}
