// Adaptee class - this is the third-party gateway with its own interface
public class StripeGateway {
    public boolean chargeCard(String cardNumber, double amount) {
        System.out.println("Stripe: Charging $" + amount + " to card " + cardNumber);
        return true;
    }

    public boolean issueRefund(String transactionId, double amount) {
        System.out.println("Stripe: Refunding $" + amount + " for transaction " + transactionId);
        return true;
    }

    public String getChargeStatus(String transactionId) {
        return "Stripe Transaction " + transactionId + " - Completed";
    }
}
