public class ObserverTest {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Test ===\n");

        // create subject objects (stocks)
        StockMarket appleStock = new StockMarket("AAPL", 150.00);
        StockMarket googleStock = new StockMarket("GOOGL", 2800.00);

        // create observer objects (apps that want to be notified)
        Observer mobileApp1 = new MobileApp("StockTracker Pro");
        Observer mobileApp2 = new MobileApp("InvestorDaily");
        Observer webApp1 = new WebApp("finance.example.com");
        Observer webApp2 = new WebApp("stocks.example.com");

        // register observers to stocks
        System.out.println("--- Registering observers ---");
        appleStock.registerObserver(mobileApp1);
        appleStock.registerObserver(webApp1);
        googleStock.registerObserver(mobileApp2);
        googleStock.registerObserver(webApp2);

        // price change triggers notification to observers
        System.out.println("\n--- Price update for AAPL ---");
        appleStock.setPrice(155.50);

        System.out.println("\n--- Price update for GOOGL ---");
        googleStock.setPrice(2850.75);

        // test removing an observer
        System.out.println("\n--- Removing an observer and updating ---");
        appleStock.removeObserver(mobileApp1);
        appleStock.setPrice(160.00);
    }
}
