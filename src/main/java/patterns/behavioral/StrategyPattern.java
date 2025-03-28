package patterns.behavioral;

public class StrategyPattern {
  public interface Strategy {
    void move();
  }

  public static class ConcreteStrategyA implements Strategy {

    @Override
    public void move() {
      System.out.println("Move by walking");
    }
  }

  public static class ConcreteStrategyB implements Strategy {

    @Override
    public void move() {
      System.out.println("Move by car");
    }
  }

  public static class Context{
    Strategy strategy;
    public Context(Strategy strategy) {
      this.strategy = strategy;
    }

    public Strategy getStrategy() {
      return strategy;
    }

    public void setStrategy(Strategy strategy) {
      this.strategy = strategy;
    }

    public static void main(String[] args){
      Strategy strategy = new ConcreteStrategyA();
      Context context = new Context(strategy);
      context.getStrategy().move();

      Strategy strategy2 = new ConcreteStrategyB();
      context.setStrategy(strategy2);
      context.getStrategy().move();
    }
  }
}
