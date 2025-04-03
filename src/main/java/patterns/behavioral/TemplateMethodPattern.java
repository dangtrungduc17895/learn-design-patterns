package patterns.behavioral;

/*
- Một lớp abstract chứa các step method thực hiện nhiệm vụ
- Những method nào cần tùy biến thì để thành abstract method cho các lớp con implement
 */
public class TemplateMethodPattern {
  public abstract static class Beverage {
    public void prepareRecipe() {
      prepareWater();
      brew();
      pourInCup();
      addCondiment();
    }

    abstract void addCondiment();

    private void pourInCup() {
      System.out.println("pourInCup");
    }

    abstract void brew();

    private void prepareWater() {
      System.out.println("Boiling water");
    }
  }

  public static class Tea extends Beverage {

    @Override
    void addCondiment() {
      System.out.println("Adding sugar");
    }

    @Override
    void brew() {
      System.out.println("Getting tea");
      System.out.println("Pour tea to water");
    }
  }

  public static class Coffee extends Beverage {

    @Override
    void addCondiment() {
      System.out.println("Adding condensed milk");
    }

    @Override
    void brew() {
      System.out.println("Getting coffee");
      System.out.println("Pour coffee to water");
    }
  }

  public static void main(String[] args){
    Coffee coffee = new Coffee();
    Tea tea = new Tea();
    coffee.prepareRecipe();
    System.out.println();
    tea.prepareRecipe();
  }
}
