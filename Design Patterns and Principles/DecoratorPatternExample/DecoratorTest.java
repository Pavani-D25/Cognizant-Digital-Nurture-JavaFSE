public class DecoratorTest {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Test ===\n");

        // basic email notification
        Notifier emailOnly = new EmailNotifier("user@email.com");
        System.out.println("--- Email Only ---");
        emailOnly.send("Server is down!");

        // email + SMS using decorators
        System.out.println("\n--- Email + SMS ---");
        Notifier emailAndSMS = new SMSNotifierDecorator(
                new EmailNotifier("user@email.com"), "+1-555-123-4567");
        emailAndSMS.send("Server is down!");

        // stack multiple decorators
        System.out.println("\n--- Email + SMS + Slack ---");
        Notifier allChannels = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier("user@email.com"), "+1-555-123-4567"),
                "alerts");
        allChannels.send("Server is down!");

        // different combination
        System.out.println("\n--- Email + Slack ---");
        Notifier emailAndSlack = new SlackNotifierDecorator(
                new EmailNotifier("user@email.com"), "general");
        emailAndSlack.send("New deployment completed!");
    }
}
