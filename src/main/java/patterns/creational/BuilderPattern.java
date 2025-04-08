package patterns.creational;

import patterns.creational.BuilderPattern.Order.Builder;

/*
- Tạo 1 Builder cho 1 Class bằng cách chứa toàn bộ các field của Class đó. Class sẽ có 1 Constructor
có tham số là this Builder.
- Những tham số required sẽ tạo ở Constructor. Các tham số optional sẽ tạo riêng từng
method-gán lại value các field đó và return this Builder
- Tạo hàm build() trong Builder return instance của Class đó
 */
public class BuilderPattern {
  public static class Order {
    private final String product;      // Bắt buộc
    private final String address;      // Bắt buộc
    private final String note;         // Tùy chọn
    private final boolean giftWrapped; // Tùy chọn

    // Constructor riêng, chỉ Builder mới truy cập được
    private Order(Builder builder) {
      this.product = builder.product;
      this.address = builder.address;
      this.note = builder.note;
      this.giftWrapped = builder.giftWrapped;
    }

    @Override
    public String toString() {
      return "Order{" +
          "product='" + product + '\'' +
          ", address='" + address + '\'' +
          ", note='" + note + '\'' +
          ", giftWrapped=" + giftWrapped +
          '}';
    }

    // Static nested Builder class
    public static class Builder {
      private String product;
      private String address;
      private String note = "";         // Giá trị mặc định
      private boolean giftWrapped = false; // Giá trị mặc định

      // Constructor với các tham số bắt buộc
      public Builder(String product, String address) {
        this.product = product;
        this.address = address;
      }

      // Phương thức cho các thuộc tính tùy chọn
      public Builder note(String note) {
        this.note = note;
        return this; // Trả về chính Builder để chain
      }

      public Builder giftWrapped(boolean giftWrapped) {
        this.giftWrapped = giftWrapped;
        return this;
      }

      // Phương thức xây dựng Product
      public Order build() {
        return new Order(this);
      }
    }
  }

  public static class OrderDirector {
    public Order createSimpleOrder(Builder builder) {
      return builder.build(); // Đơn hàng cơ bản
    }

    public Order createGiftOrder(Builder builder) {
      return builder
          .note("Gói quà cẩn thận nhé!")
          .giftWrapped(true)
          .build(); // Đơn hàng có gói quà
    }
  }

  public static void main(String[] args) {
    // Tạo Builder
    Order.Builder builder = new Order.Builder("Sách Java", "123 Đường Láng, Hà Nội");

    // Tạo đơn hàng cơ bản
    Order simpleOrder = builder.build();
    System.out.println("Đơn hàng cơ bản: " + simpleOrder);

    // Tạo đơn hàng với tùy chọn
    Order customOrder = builder
        .note("Giao hàng trước 5 giờ chiều.")
        .giftWrapped(true)
        .build();
    System.out.println("Đơn hàng tùy chỉnh: " + customOrder);

    // Sử dụng Director
    OrderDirector director = new OrderDirector();
    Order giftOrder = director.createGiftOrder(new Order.Builder("Hoa hồng", "456 Nguyễn Trãi, TP.HCM"));
    System.out.println("Đơn hàng gói quà: " + giftOrder);
  }
}
