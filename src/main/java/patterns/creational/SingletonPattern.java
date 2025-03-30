package patterns.creational;

public class SingletonPattern {
  private static SingletonPattern instance;

  private SingletonPattern() {}

  public static SingletonPattern getInstance() {
    if (instance == null) {
      instance = new SingletonPattern();
    }
    return instance;
  }

  public static void main(String[] args) {
    SingletonPattern s1 = SingletonPattern.getInstance();
    SingletonPattern s2 = SingletonPattern.getInstance();
    System.out.println("Is the same Object: " + (s1 == s2));
  }
}
