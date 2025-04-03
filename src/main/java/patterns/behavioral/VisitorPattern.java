package patterns.behavioral;

/*
- Tách biệt các function với các đối tượng
- Use 1 visitor class để thực thi các function tuy theo đối tượng với 1 method visit với
tham số là interface của các đối tượng. Trong hàm này sẽ check loại đối tượng để thực thi chức năng
tương ứng
- Các đối tượng có 1 method accept với tham số là Visitor
 */
public class VisitorPattern {
  public interface Visitor{
    void visit(Member member);
  }

  public interface Member {
    void accept(Visitor visitor);
  }

  public static class BasicMember implements Member {

    @Override
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }

  public static class VipMember implements Member {

    @Override
    public void accept(Visitor visitor) {
      visitor.visit(this);
    }
  }

  public static class ConcreteVisitor implements Visitor {

    @Override
    public void visit(Member member) {
      if (member instanceof VipMember) {
        System.out.println("VipMember");
      }else if (member instanceof BasicMember) {
        System.out.println("BasicMember");
      }
    }

  }

  public static void main(String[] args){
    Member basicMember = new BasicMember();
    Member vipMember = new VipMember();

    Visitor visitor = new ConcreteVisitor();
    basicMember.accept(visitor);
    vipMember.accept(visitor);
  }
}
