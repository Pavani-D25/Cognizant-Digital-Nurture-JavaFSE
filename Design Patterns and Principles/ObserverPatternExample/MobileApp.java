// Concrete observer - mobile app that receives stock updates
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String symbol, double price) {
        System.out.println("[MobileApp: " + appName + "] " + symbol + " is now $" + price);
    }
}
