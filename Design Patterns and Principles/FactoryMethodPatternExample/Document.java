// Document interface - all document types must implement this
public interface Document {
    void open();
    void close();
    void save();
    String getType();
}
