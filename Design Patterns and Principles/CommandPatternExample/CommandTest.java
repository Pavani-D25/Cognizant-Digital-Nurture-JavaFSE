public class CommandTest {
    public static void main(String[] args) {
        System.out.println("=== Command Pattern Test ===\n");

        // create receiver objects
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        // create invoker
        RemoteControl remote = new RemoteControl();

        // create command objects
        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);
        Command bedroomOff = new LightOffCommand(bedroomLight);

        // execute commands
        System.out.println("--- Turning on Living Room light ---");
        remote.setCommand(livingRoomOn);
        remote.pressButton();

        System.out.println("\n--- Turning on Bedroom light ---");
        remote.setCommand(bedroomOn);
        remote.pressButton();

        System.out.println("\n--- Turning off Living Room light ---");
        remote.setCommand(livingRoomOff);
        remote.pressButton();

        // test undo functionality
        System.out.println("\n--- Undo last command ---");
        remote.pressUndo();

        System.out.println("\n--- Undo again ---");
        remote.pressUndo();

        // show command history
        remote.showHistory();
    }
}
