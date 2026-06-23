// Concrete strategy - PayPal payment implementation
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paying $" + amount + " using PayPal account: " + email);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }
}
