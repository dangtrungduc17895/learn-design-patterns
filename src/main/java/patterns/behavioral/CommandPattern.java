package patterns.behavioral;

public class CommandPattern {

  public static class Light {
    private String location;

    public Light(String location) {
      this.location = location;
    }

    public void turnOn() {
      System.out.println(location + " đèn bật.");
    }

    public void turnOff() {
      System.out.println(location + " đèn tắt.");
    }
  }

  public interface Command {
    void execute();
    void undo();
  }

  public static class LightOnCommand implements Command {
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
      light.turnOff();
    }
  }

  public static class LightOffCommand implements Command {
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
      light.turnOn();
    }
  }

  public static class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
      this.command = command;
    }

    public void pressButton() {
      command.execute();
    }

    public void pressUndo() {
      command.undo();
    }
  }

  public static void main(String[] args) {
    // Tạo receiver
    Light livingRoomLight = new Light("Phòng khách");

    // Tạo các command
    Command lightOn = new LightOnCommand(livingRoomLight);
    Command lightOff = new LightOffCommand(livingRoomLight);

    // Tạo invoker
    RemoteControl remote = new RemoteControl();

    // Bật đèn
    remote.setCommand(lightOn);
    remote.pressButton();
    remote.pressUndo();

    System.out.println("---");

    // Tắt đèn
    remote.setCommand(lightOff);
    remote.pressButton();
    remote.pressUndo();
  }
}
