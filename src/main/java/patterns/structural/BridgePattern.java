package patterns.structural;

public class BridgePattern {

  public interface Eatable {
    public void eat();
  }

  public interface Drinkable {
    public void drink();
  }

  static void doFeed(Eatable eatable, Drinkable drinkable) {
    eatable.eat();
    drinkable.drink();
  }

  public static class Dog implements Drinkable, Eatable {

    @Override
    public void drink() {
      System.out.println("Drink water");
    }

    @Override
    public void eat() {
      System.out.println("Eat meat");
    }
  }

  public static class Cat implements Drinkable, Eatable {

    @Override
    public void drink() {
      System.out.println("Drink milk");
    }

    @Override
    public void eat() {
      System.out.println("Eat fish");
    }
  }

  public static void main(String[] args) {
    BridgePattern.doFeed(new Dog(), new Dog());
  }
}
