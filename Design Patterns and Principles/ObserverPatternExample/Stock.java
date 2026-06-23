// Subject interface - maintains list of observers and notifies them
public interface Stock {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
    String getSymbol();
    double getPrice();
}
