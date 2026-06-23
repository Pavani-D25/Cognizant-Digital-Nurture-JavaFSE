import java.util.ArrayList;
import java.util.List;

// Concrete Subject - maintains state and notifies observers when price changes
public class StockMarket implements Stock {
    private String symbol;
    private double price;
    private List<Observer> observers;

    public StockMarket(String symbol, double initialPrice) {
        this.symbol = symbol;
        this.price = initialPrice;
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered for " + symbol);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed for " + symbol);
    }

    @Override
    public void notifyObservers() {
        // notify all registered observers about price change
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        System.out.println("\n" + symbol + " price changed from $" + this.price + " to $" + price);
        this.price = price;
        notifyObservers();  // notify observers when price changes
    }
}
