package patterns.behavioral;

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
