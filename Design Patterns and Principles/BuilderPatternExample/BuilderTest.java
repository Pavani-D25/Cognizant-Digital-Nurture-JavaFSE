public class BuilderTest {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Test ===\n");

        // build a gaming PC with all options
        Computer gamingPC = new Computer.Builder()
                .cpu("Intel Core i9-13900K")
                .ram("32GB DDR5")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .motherboard("ASUS ROG Maximus")
                .powerSupply("850W Corsair")
                .build();

        // build an office PC with some options
        Computer officePC = new Computer.Builder()
                .cpu("Intel Core i5-13400")
                .ram("16GB DDR4")
                .storage("512GB SSD")
                .motherboard("MSI B660")
                .powerSupply("550W EVGA")
                .build();

        // build a basic PC with only required fields
        Computer basicPC = new Computer.Builder()
                .cpu("AMD Ryzen 5 5600")
                .ram("8GB DDR4")
                .storage("256GB SSD")
                .build();

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("\nOffice PC: " + officePC);
        System.out.println("\nBasic PC: " + basicPC);
    }
}
