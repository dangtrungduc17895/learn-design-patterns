package patterns.structural;

/*
Tạo 1 interface chứa các method xử lý những yêu cầu của client, che giấu đi logic giúp đơn giản hoá yêu cầu
cho client. Ứng dụng thực tiễn chính là các lớp service trong dự án.
 */
public class FacadePattern {

  public interface UserService {
    String getUserNameById(int id);
  }

  public static class UserServiceImpl implements UserService {
    @Override
    public String getUserNameById(int id) {
      return "My name";
    }
  }

  public static class UseCaseExample {
    UserService userService;

    public UseCaseExample(UserServiceImpl userService) {
      this.userService = userService;
    }

    public static void main(String[] args) {
      UseCaseExample example = new UseCaseExample(new UserServiceImpl());
      System.out.println(example.userService.getUserNameById(1));
      ;
    }
  }
}
