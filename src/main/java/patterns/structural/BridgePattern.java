package patterns.structural;

public class BridgePattern {

  public abstract static class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
      this.device = device;
    }

    public void turnOn() {
      device.turnOn();
    }

    public void turnOff() {
      device.turnOff();
    }

    public abstract void adjustVolume(int volume);
  }

  public static class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
      super(device);
    }

    @Override
    public void adjustVolume(int volume) {
      device.setVolume(volume);
    }
  }

  public static class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
      super(device);
    }

    @Override
    public void adjustVolume(int volume) {
      device.setVolume(volume + 10);
      System.out.println("Advanced Remote: Tăng âm lượng thêm 10!");
    }

    public void mute() {
      device.setVolume(0);
      System.out.println("Advanced Remote: Tắt tiếng!");
    }
  }

  public interface Device {
    void turnOn();

    void turnOff();

    void setVolume(int volume);
  }

  public static class TV implements Device {
    private int volume;

    @Override
    public void turnOn() {
      System.out.println("Bật TV...");
    }

    @Override
    public void turnOff() {
      System.out.println("Tắt TV...");
    }

    @Override
    public void setVolume(int volume) {
      this.volume = volume;
      System.out.println("Đặt âm lượng TV ở mức: " + volume);
    }
  }

  public static class Radio implements Device {
    private int volume;

    @Override
    public void turnOn() {
      System.out.println("Bật Radio...");
    }

    @Override
    public void turnOff() {
      System.out.println("Tắt Radio...");
    }

    @Override
    public void setVolume(int volume) {
      this.volume = volume;
      System.out.println("Đặt âm lượng Radio ở mức: " + volume);
    }
  }

  public static void main(String[] args) {
    // Tạo các thiết bị
    Device tv = new TV();
    Device radio = new Radio();

    // Sử dụng Basic Remote với TV
    System.out.println("Sử dụng Basic Remote với TV:");
    RemoteControl basicRemote = new BasicRemote(tv);
    basicRemote.turnOn();
    basicRemote.adjustVolume(20);
    basicRemote.turnOff();

    System.out.println("\nSử dụng Advanced Remote với Radio:");
    // Sử dụng Advanced Remote với Radio
    RemoteControl advancedRemote = new AdvancedRemote(radio); // Khai báo kiểu AdvancedRemote
    advancedRemote.turnOn();
    advancedRemote.adjustVolume(15);
    ((AdvancedRemote) advancedRemote).mute(); // Gọi trực tiếp trên AdvancedRemote
    advancedRemote.turnOff();
  }
}
