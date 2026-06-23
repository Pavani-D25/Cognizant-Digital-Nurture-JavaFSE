// Singleton Pattern - ensures only one instance of Logger exists
public class Logger {
    // static variable to hold the single instance
    private static Logger instance;
    private String logLevel;

    // private constructor prevents instantiation from other classes
    private Logger() {
        this.logLevel = "INFO";
        System.out.println("Logger instance created.");
    }

    // double-checked locking for thread safety
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void log(String message) {
        System.out.println("[" + logLevel + "] " + message);
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
