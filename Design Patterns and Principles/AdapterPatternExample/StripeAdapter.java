// Adapter class - translates PaymentProcessor calls to StripeGateway methods
public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    private String cardNumber;
    private String lastTransactionId;

    public StripeAdapter(StripeGateway stripeGateway, String cardNumber) {
        this.stripeGateway = stripeGateway;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        lastTransactionId = "STRIPE-" + System.currentTimeMillis();
        return stripeGateway.chargeCard(cardNumber, amount);
    }

    @Override
    public boolean refundPayment(double amount) {
        return stripeGateway.issueRefund(lastTransactionId, amount);
    }

    @Override
    public String getTransactionStatus() {
        return stripeGateway.getChargeStatus(lastTransactionId);
    }
}
