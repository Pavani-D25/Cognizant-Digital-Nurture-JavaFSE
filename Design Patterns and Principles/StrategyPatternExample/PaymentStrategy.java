// Strategy interface - defines common interface for all payment methods
public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentMethod();
}
