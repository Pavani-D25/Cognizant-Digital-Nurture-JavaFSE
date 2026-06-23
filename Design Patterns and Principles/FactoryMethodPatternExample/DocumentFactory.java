// abstract factory class - subclasses will implement createDocument()
public abstract class DocumentFactory {
    // factory method - subclasses must implement this
    public abstract Document createDocument();

    // template method that uses the factory method
    public Document processDocument() {
        Document document = createDocument();
        document.open();
        return document;
    }
}
