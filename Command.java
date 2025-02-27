
/*
  - The Command Pattern is a behavioral design pattern used to encapsulate a request as an object,
     allowing you to parameterize clients with different requests, queue requests, and log the history of executed operations.
   - The Command Pattern is used to decouple the sender and receiver of a request.
   - The Command Pattern is used to parameterize objects with requests.
   - The Command Pattern is used to queue requests.
   - When you need to decouple the sender (Invoker) and the receiver (Action Executor).
   - When implementing Undo/Redo functionality in applications (e.g., text editors).
   - When handling remote control operations (e.g., turning TV on/off).
   - When implementing Macro Commands (grouping multiple commands).
 */

interface ICommand {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

class TurnOnBulbCommand implements ICommand {
    private Light light;

    public TurnOnBulbCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffBulbCommand implements ICommand {
    private Light light;

    public TurnOffBulbCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker class
class RemoteControl {
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
public class Command {
    public static void main(String[] args) {
        Light light = new Light();
        ICommand turnOn = new TurnOnBulbCommand(light);
        ICommand turnOff = new TurnOffBulbCommand(light);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(turnOn);
        // Switch on
        remote.pressButton();

        remote.setCommand(turnOff);
        // Switch off
        remote.pressButton();
    }
}
