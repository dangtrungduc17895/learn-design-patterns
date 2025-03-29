package patterns.structural;

/*
- Add more features to the object without change it
- Use a wrapper
- Override method
 */
public class DecoratorPattern {

  public interface Coffee {
    String getMyCoffee();
  }

  public static class OriginalCoffee implements Coffee {

    @Override
    public String getMyCoffee() {
      return "Original Coffee";
    }
  }

  public abstract static class DecoratorCoffee implements Coffee {
    private Coffee coffee;

    public Coffee getCoffee() {
      return coffee;
    }

    public DecoratorCoffee(Coffee coffee) {
      this.coffee = coffee;
    }
  }

  public static class MilkDecoratorCoffee extends DecoratorCoffee {
    public MilkDecoratorCoffee(Coffee coffee) {
      super(coffee);
    }

    @Override
    public String getMyCoffee() {
      return getCoffee().getMyCoffee() + " and Milk";
    }
  }

  public static class SugarDecoratorCoffee extends DecoratorCoffee {
    public SugarDecoratorCoffee(Coffee coffee) {
      super(coffee);
    }

    @Override
    public String getMyCoffee() {
      return getCoffee().getMyCoffee() + " and Sugar";
    }
  }

  public static class SaltDecoratorCoffee extends DecoratorCoffee {
    public SaltDecoratorCoffee(Coffee coffee) {
      super(coffee);
    }

    @Override
    public String getMyCoffee() {
      return getCoffee().getMyCoffee() + " and Salt";
    }
  }

  public static void main(String[] args) {
    OriginalCoffee originalCoffee = new OriginalCoffee();
    System.out.println(originalCoffee.getMyCoffee());

    Coffee coffee = new SugarDecoratorCoffee(new MilkDecoratorCoffee(originalCoffee));

    System.out.println(coffee.getMyCoffee());
  }
}
