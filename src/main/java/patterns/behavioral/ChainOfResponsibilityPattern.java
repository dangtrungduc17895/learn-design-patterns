package patterns.behavioral;

/*
Tạo một chuỗi các đối tượng xử lý, mỗi đối tượng có cơ hội xử lý yêu cầu
hoặc chuyển nó cho đối tượng tiếp theo. Điều này giúp tách biệt logic xử lý
và tránh việc một lớp phải xử lý mọi trường hợp.
 */
public class ChainOfResponsibilityPattern {

  public interface Handler {
    void handle(SomeRequest request);

    void setNextHandler(Handler nextHandler);
  }

  public abstract static class AbstractHandler implements Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
      this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
      return nextHandler;
    }
  }

  public static class HandlerA extends AbstractHandler {

    @Override
    public void handle(SomeRequest request) {
      if (request.condition.equals("A")) {
        System.out.println("A can handle");
      } else if (getNextHandler() != null) {
        getNextHandler().handle(request);
      }
    }
  }

  public static class HandlerB extends AbstractHandler {

    @Override
    public void handle(SomeRequest request) {
      if (request.condition.equals("B")) {
        System.out.println("B can handle");
      } else if (getNextHandler() != null) {
        getNextHandler().handle(request);
      }
    }
  }

  public static class SomeRequest {
    String condition;

    public SomeRequest(String condition) {
      this.condition = condition;
    }
  }

  public static void main(String[] args){
    Handler handlerA = new HandlerA();
    Handler handlerB = new HandlerB();
    handlerA.setNextHandler(handlerB);
    SomeRequest request = new SomeRequest("A");
    SomeRequest requestB = new SomeRequest("B");
    handlerA.handle(requestB);
  }
}
