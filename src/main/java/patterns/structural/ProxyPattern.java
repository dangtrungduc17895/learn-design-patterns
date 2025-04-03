package patterns.structural;
/*
- Tạo 1 interface
- Cho realObject implement interface này
- Create proxyClass implement kế thừa interface, chứa 1 realObject. Triển khai method
bọc lấy method của realObject
 */
public class ProxyPattern {
  public interface Image{
    void display();
  }

  public static class RealImage implements Image{
    String image;
    public RealImage(String image) {
      this.image = image;
      loading();
    }

    private void loading() {
      System.out.println("Loading...");
    }

    public void display() {
      System.out.println("Displaying...");
    }
  }

  public static class ProxyImage implements Image{
    String image;
    RealImage realImage;
    public ProxyImage(String image){
      this.image = image;
    }

    @Override
    public void display() {
      if (realImage == null){
        realImage = new RealImage(image);
      }
      realImage.display();
    }
  }

  public static void main(String[] args){
    RealImage realImage = new RealImage("image");
    ProxyImage proxyImage = new ProxyImage("image1");
    realImage.display();
    proxyImage.display();
    proxyImage.display();
  }
}
