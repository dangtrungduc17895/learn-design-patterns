package patterns.creational;

public class FactoryMethodPattern {

  public Parent getInstance(ChildentType type) {
    return switch (type) {
      case A -> new ChildentA();
      case B -> new ChildentB();
    };
  }

  public static void main(String[] args){
    FactoryMethodPattern obj = new FactoryMethodPattern();
    Parent p = obj.getInstance(ChildentType.A);
    p.sayMyName();

    Parent p2 = obj.getInstance(ChildentType.B);
    p2.sayMyName();
  }

  public static class ChildentA implements Parent {

    @Override
    public void sayMyName() {
      System.out.println("My name is A");
    }
  }

  public static class ChildentB implements Parent { @Override
  public void sayMyName() {
    System.out.println("My name is B");
  }}

  public interface Parent {
    void sayMyName();
  }

  public enum ChildentType {
    A,
    B;
  }
}
