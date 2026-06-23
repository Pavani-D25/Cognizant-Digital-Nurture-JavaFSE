public class StrategyTest {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Test ===\n");

        PaymentContext paymentContext = new PaymentContext();

        // test credit card payment
        System.out.println("--- Payment with Credit Card ---");
        PaymentStrategy creditCard = new CreditCardPayment("4111-1111-1111-1234", "John Doe", "123");
        paymentContext.setPaymentStrategy(creditCard);
        paymentContext.addAmount(250.00);
        paymentContext.addAmount(150.00);
        paymentContext.executePayment();

        // test PayPal payment
        System.out.println("\n--- Payment with PayPal ---");
        PaymentContext paypalContext = new PaymentContext();
        PaymentStrategy paypal = new PayPalPayment("user@example.com", "password123");
        paypalContext.setPaymentStrategy(paypal);
        paypalContext.addAmount(99.99);
        paypalContext.addAmount(49.99);
        paypalContext.executePayment();

        // demonstrate changing strategy at runtime
        System.out.println("\n--- Changing payment strategy at runtime ---");
        PaymentContext dynamicContext = new PaymentContext();
        dynamicContext.addAmount(500.00);

        System.out.println("Initially using Credit Card:");
        dynamicContext.setPaymentStrategy(creditCard);
        dynamicContext.executePayment();

        // switch to different payment method
        System.out.println("\nSwitching to PayPal:");
        dynamicContext.setPaymentStrategy(paypal);
        dynamicContext.executePayment();
    }
}
