package patterns.structural;
/*
-Chuyển đổi giao diện của một lớp thành một giao diện khác mà client mong đợi.
-Giúp các lớp không tương thích (do giao diện khác nhau)
có thể hoạt động cùng nhau mà không cần thay đổi mã nguồn của chúng.
 */
public class AdapterPattern {

  //Adaptee
  public static class OldPayment{
    public double payUSD(double amount) {
      return amount;
    }
  }

  public  interface NewPayment {
    double processPayment(double amount, String currency);
  }

  public static class PaymentAdapter implements NewPayment {
    private static final double USD_TO_EUR_RATE = 0.85;
    OldPayment oldPayment;

    public PaymentAdapter(OldPayment oldPayment) {
      this.oldPayment = oldPayment;
    }

    @Override
    public double processPayment(double amount, String currency) {
      if ("USD".equals(currency)) {
        return oldPayment.payUSD(amount);
      } else if ("EUR".equals(currency)) {
        return amount / USD_TO_EUR_RATE;
      }
      return 0;
    }
  }

  public static void main(String[] args){
    OldPayment oldPayment = new OldPayment();
    PaymentAdapter paymentAdapter = new PaymentAdapter(oldPayment);
    System.out.println("USD: " + oldPayment.payUSD(100));
    System.out.println("EUR: " + paymentAdapter.processPayment(100, "EUR"));
  }
}
