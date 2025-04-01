package patterns.creational;

/*
- Flexible for adding new field to my Object
 */
public class OptionalPattern {
  public interface Option {
    String getName();
  }

  public static class MyService {
    Option optionA;
    Option optionB;
    Option optionC;

    public static MyService create(Option... options) {
      MyService myService = new MyService();
      for (Option option : options) {
        switch (option.getName()) {
          case "A":
            myService.optionA = option;
            break;
          case "B":
            myService.optionB = option;
            break;
          case "C":
            myService.optionC = option;
          default:
            break;
        }
      }
      return myService;
    }
  }

  public static class OptionA implements Option {

    @Override
    public String getName() {
      return "A";
    }
  }

  public static class OptionB implements Option {

    @Override
    public String getName() {
      return "B";
    }
  }

  public static class OptionC implements Option {

    @Override
    public String getName() {
      return "C";
    }
  }

  public static void main(String[] args) {

    MyService myService = MyService.create(new OptionC(),new OptionB(), new OptionA());

    int a = 3;
  }
}
