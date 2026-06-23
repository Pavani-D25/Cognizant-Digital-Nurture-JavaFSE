// Target interface - this is what our system expects
public interface PaymentProcessor {
    boolean processPayment(double amount);
    boolean refundPayment(double amount);
    String getTransactionStatus();
}
