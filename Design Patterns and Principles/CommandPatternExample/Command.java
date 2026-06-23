// Command interface - encapsulates a request as an object
public interface Command {
    void execute();
    void undo();
    String getDescription();
}
