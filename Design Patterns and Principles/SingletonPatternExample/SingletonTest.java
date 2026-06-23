public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test ===\n");

        // creating two references to the same instance
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // check if both references point to same object
        System.out.println("Logger1 hashcode: " + logger1.hashCode());
        System.out.println("Logger2 hashcode: " + logger2.hashCode());
        System.out.println("Are both instances same? " + (logger1 == logger2));

        // changing log level on one reference affects both
        logger1.setLogLevel("DEBUG");
        System.out.println("\nLogger1 log level: " + logger1.getLogLevel());
        System.out.println("Logger2 log level: " + logger2.getLogLevel());

        // test logging methods
        logger1.log("Application started");
        logger2.log("Processing data");
        logger1.info("User logged in");
        logger2.warn("Low memory warning");
        logger1.error("Connection failed");

        System.out.println("\nBoth references point to the same instance: " + (logger1 == logger2));
    }
}
