// Concrete decorator - adds SMS notification functionality
public class SMSNotifierDecorator extends NotifierDecorator {
    private String phoneNumber;

    public SMSNotifierDecorator(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        super.send(message);  // call wrapped notifier first
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
