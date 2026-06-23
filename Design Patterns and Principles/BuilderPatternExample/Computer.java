// Builder Pattern - Computer class with nested Builder
public class Computer {
    // required fields
    private String cpu;
    private String ram;
    private String storage;

    // optional fields
    private String gpu;
    private String motherboard;
    private String powerSupply;

    // private constructor - only Builder can create Computer objects
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
    }

    // getters
    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getMotherboard() { return motherboard; }
    public String getPowerSupply() { return powerSupply; }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage +
                ", GPU=" + gpu + ", Motherboard=" + motherboard + ", PowerSupply=" + powerSupply + "]";
    }

    // static nested Builder class
    public static class Builder {
        // required parameters
        private String cpu;
        private String ram;
        private String storage;

        // optional parameters - initialized to default values
        private String gpu;
        private String motherboard;
        private String powerSupply;

        // builder methods return this for chaining
        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        // build method returns the final Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}
