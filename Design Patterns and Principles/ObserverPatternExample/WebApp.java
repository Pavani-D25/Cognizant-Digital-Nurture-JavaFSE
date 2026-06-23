// Concrete observer - web application that receives stock updates
public class WebApp implements Observer {
    private String url;

    public WebApp(String url) {
        this.url = url;
    }

    @Override
    public void update(String symbol, double price) {
        System.out.println("[WebApp: " + url + "] " + symbol + " is now $" + price);
    }
}
