// Observer interface - gets notified when subject state changes
public interface Observer {
    void update(String symbol, double price);
}
