import java.util.ArrayList;
import java.util.List;

// Invoker class - asks the command to carry out the request
public class RemoteControl {
    private Command command;
    private List<Command> commandHistory;  // keeps track of executed commands

    public RemoteControl() {
        this.commandHistory = new ArrayList<>();
    }

    public void setCommand(Command command) {
        this.command = command;
        System.out.println("Remote: Command set - " + command.getDescription());
    }

    public void pressButton() {
        if (command != null) {
            System.out.println("Remote: Pressing button...");
            command.execute();
            commandHistory.add(command);  // save to history for undo
        } else {
            System.out.println("Remote: No command set!");
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            System.out.println("Remote: Undoing last command...");
            lastCommand.undo();
        } else {
            System.out.println("Remote: No commands to undo!");
        }
    }

    public void showHistory() {
        System.out.println("\n--- Command History ---");
        for (int i = 0; i < commandHistory.size(); i++) {
            System.out.println((i + 1) + ". " + commandHistory.get(i).getDescription());
        }
    }
}
