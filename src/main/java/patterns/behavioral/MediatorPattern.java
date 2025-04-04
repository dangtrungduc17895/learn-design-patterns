package patterns.behavioral;

/*
- Create an interface Mediator
- Concrete this interface, includes list object need communicate. Implement method navigate these object
- Create Object has field is this interface, call this method of interface
 */
public class MediatorPattern {

  public interface TrafficLightMediator {
    void notifyLight(String lightColor, String action);
  }

  public static class TrafficController implements TrafficLightMediator {
    private TrafficLight redLight;
    private TrafficLight yellowLight;
    private TrafficLight greenLight;

    public TrafficController(TrafficLight red, TrafficLight yellow, TrafficLight green) {
      this.redLight = red;
      this.yellowLight = yellow;
      this.greenLight = green;

      // Thiết lập mediator cho các đèn
      this.redLight.setMediator(this);
      this.yellowLight.setMediator(this);
      this.greenLight.setMediator(this);
    }

    @Override
    public void notifyLight(String lightColor, String action) {
      if ("Red".equals(lightColor) && "ON".equals(action)) {
        yellowLight.turnOff();
        greenLight.turnOff();
        System.out.println("Đèn đỏ bật, các đèn khác tắt.");
      } else if ("Yellow".equals(lightColor) && "ON".equals(action)) {
        redLight.turnOff();
        greenLight.turnOff();
        System.out.println("Đèn vàng bật, các đèn khác tắt.");
      } else if ("Green".equals(lightColor) && "ON".equals(action)) {
        redLight.turnOff();
        yellowLight.turnOff();
        System.out.println("Đèn xanh bật, các đèn khác tắt.");
      }
    }
  }

  public static class TrafficLight {
    private String color;
    private boolean isOn;
    private TrafficLightMediator mediator;

    public TrafficLight(String color) {
      this.color = color;
      this.isOn = false;
    }

    public void setMediator(TrafficLightMediator mediator) {
      this.mediator = mediator;
    }

    public void turnOn() {
      if (!isOn) {
        isOn = true;
        System.out.println("Đèn " + color + " bật.");
        mediator.notifyLight(color, "ON"); // Thông báo mediator
      } else {
        System.out.println("Đèn " + color + " đã bật rồi.");
      }
    }

    public void turnOff() {
      if (isOn) {
        isOn = false;
        System.out.println("Đèn " + color + " tắt.");
      }
    }
  }

  public static void main(String[] args) {
    // Tạo các đèn giao thông
    TrafficLight red = new TrafficLight("Red");
    TrafficLight yellow = new TrafficLight("Yellow");
    TrafficLight green = new TrafficLight("Green");

    // Tạo mediator và liên kết các đèn
    TrafficLightMediator controller = new TrafficController(red, yellow, green);

    // Thử bật các đèn
    green.turnOn();
    System.out.println("---");
    yellow.turnOn();
    System.out.println("---");
    red.turnOn();
  }
}
