// Concrete command - turns the light off
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();  // undo turns the light on
    }

    @Override
    public String getDescription() {
        return "Turn off " + light.getLocation() + " light";
    }
}
