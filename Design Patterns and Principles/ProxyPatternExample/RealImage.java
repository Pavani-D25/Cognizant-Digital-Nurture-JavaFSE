// Real subject - loads image from remote server (expensive operation)
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromRemoteServer();  // simulate loading from remote server
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image from remote server: " + fileName);
        // simulate network delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image loaded successfully: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
