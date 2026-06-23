public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Test ===\n");

        // create proxy images - note that RealImage is NOT loaded yet
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        Image image3 = new ProxyImage("photo1.jpg");  // same file as image1

        // first display - this will load the image from remote server
        System.out.println("--- First display of photo1.jpg ---");
        image1.display();

        // first display of photo2 - loads from server
        System.out.println("\n--- First display of photo2.jpg ---");
        image2.display();

        // second display - should use cached version
        System.out.println("\n--- Second display of photo1.jpg (should use cache) ---");
        image1.display();

        // different reference but same file - should also use cache
        System.out.println("\n--- Display photo1.jpg again from different reference ---");
        image3.display();

        System.out.println("\n--- All images have been loaded ---");
    }
}
