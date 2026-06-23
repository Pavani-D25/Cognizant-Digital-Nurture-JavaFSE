// Concrete command - turns the light on
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();  // undo turns the light off
    }

    @Override
    public String getDescription() {
        return "Turn on " + light.getLocation() + " light";
    }
}
