package patterns.creational;

/*
- Chỉ tồn tại 1 instance duy nhất của class đó trong hệ thống
- Kiểm tra khởi tạo 1 lần
- Tắt tất cả các hàm thay đổi objetc đó
 */
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
