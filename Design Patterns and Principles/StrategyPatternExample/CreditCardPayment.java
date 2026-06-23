// Concrete strategy - credit card payment implementation
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paying $" + amount + " using Credit Card ending in " +
                cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Card Holder: " + cardHolderName);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }
}
