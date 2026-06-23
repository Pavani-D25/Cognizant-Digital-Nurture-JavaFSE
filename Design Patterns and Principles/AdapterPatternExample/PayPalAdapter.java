// Adapter for PayPal - translates our interface to PayPal's methods
public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    private String email;
    private String lastPaymentId;

    public PayPalAdapter(PayPalGateway payPalGateway, String email) {
        this.payPalGateway = payPalGateway;
        this.email = email;
    }

    @Override
    public boolean processPayment(double amount) {
        lastPaymentId = "PAYPAL-" + System.currentTimeMillis();
        return payPalGateway.makePayment(email, amount);
    }

    @Override
    public boolean refundPayment(double amount) {
        return payPalGateway.cancelPayment(lastPaymentId);
    }

    @Override
    public String getTransactionStatus() {
        return payPalGateway.checkStatus(lastPaymentId);
    }
}
