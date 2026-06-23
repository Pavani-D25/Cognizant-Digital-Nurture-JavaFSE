// another Adaptee class - PayPal has different method names
public class PayPalGateway {
    public boolean makePayment(String email, double amount) {
        System.out.println("PayPal: Processing payment of $" + amount + " from " + email);
        return true;
    }

    public boolean cancelPayment(String paymentId) {
        System.out.println("PayPal: Cancelling payment " + paymentId);
        return true;
    }

    public String checkStatus(String paymentId) {
        return "PayPal Payment " + paymentId + " - Success";
    }
}
