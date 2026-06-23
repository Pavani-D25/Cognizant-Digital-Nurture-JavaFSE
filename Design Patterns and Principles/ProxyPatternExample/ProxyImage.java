import java.util.HashMap;
import java.util.Map;

// Proxy class - controls access to RealImage with lazy loading and caching
public class ProxyImage implements Image {
    private String fileName;
    private RealImage realImage;
    private static Map<String, RealImage> imageCache = new HashMap<>();  // cache for loaded images

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            // lazy initialization - only load when needed
            if (imageCache.containsKey(fileName)) {
                System.out.println("Image found in cache: " + fileName);
                realImage = imageCache.get(fileName);
            } else {
                System.out.println("Creating new image instance for: " + fileName);
                realImage = new RealImage(fileName);
                imageCache.put(fileName, realImage);  // add to cache
            }
        } else {
            System.out.println("Using existing image instance for: " + fileName);
        }
        realImage.display();
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
