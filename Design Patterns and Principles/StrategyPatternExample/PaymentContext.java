// Context class - holds reference to strategy and executes it
public class PaymentContext {
    private PaymentStrategy paymentStrategy;  // strategy can be changed at runtime
    private double totalAmount;

    public PaymentContext() {
        this.totalAmount = 0;
    }

    // setter for strategy - allows changing payment method at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void addAmount(double amount) {
        this.totalAmount += amount;
    }

    public boolean executePayment() {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy set!");
            return false;
        }
        System.out.println("Payment Method: " + paymentStrategy.getPaymentMethod());
        System.out.println("Total Amount: $" + totalAmount);
        // delegate payment to the strategy
        boolean result = paymentStrategy.pay(totalAmount);
        if (result) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed!");
        }
        return result;
    }
}
