package patterns.creational;

/*
Clone an exist object
Cần lưu ý 2 kiểu copy sau
- Shallow copy: nông
- Deep copy: sâu
 */
public class PrototypePattern {
  public interface Prototype {
    Prototype clone() throws CloneNotSupportedException;
  }

  public static class A implements Prototype {
    private String name;

    public A(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public Prototype clone() throws CloneNotSupportedException {
        return new A(this.name);
    }
  }

  public static void main(String[] args) throws CloneNotSupportedException {
    A a = new A("a");
    A b = (A) a.clone();
    a.setName("b");
    System.out.println(a.getName());
    System.out.println(b.getName());
  }
}
