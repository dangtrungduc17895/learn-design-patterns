package patterns.behavioral;
/*
- Interface State chứa các phương thức cần implement
- Các Concrete State sẽ triển khai các method, và change sang các State phù hợp
- Một Class chưa 1 object State, class này sẽ là tham số cho các method trong Interface State
 */
public class StatePattern {
  public interface State {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispenseProduct(VendingMachine machine);
  }

  public static class VendingMachine {
    private State currentState;
    private int coinCount;

    public VendingMachine() {
      this.currentState = new NoCoinState(); // Trạng thái ban đầu
      this.coinCount = 0;
    }

    public void setState(State state) {
      this.currentState = state;
    }

    public void insertCoin() {
      currentState.insertCoin(this);
    }

    public void selectProduct() {
      currentState.selectProduct(this);
    }

    public void dispenseProduct() {
      currentState.dispenseProduct(this);
    }

    public void addCoin() {
      coinCount++;
    }

    public void resetCoins() {
      coinCount = 0;
    }

    public int getCoinCount() {
      return coinCount;
    }
  }

  public static class NoCoinState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
      machine.addCoin();
      System.out.println("Đã bỏ xu. Số xu: " + machine.getCoinCount());
      machine.setState(new HasCoinState());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
      System.out.println("Vui lòng bỏ xu trước khi chọn sản phẩm.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
      System.out.println("Không có xu, không thể giao sản phẩm.");
    }
  }

  public static class HasCoinState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
      machine.addCoin();
      System.out.println("Đã bỏ thêm xu. Số xu: " + machine.getCoinCount());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
      if (machine.getCoinCount() >= 2) { // Giả sử cần 2 xu để mua sản phẩm
        System.out.println("Đã chọn sản phẩm.");
        machine.setState(new DispensingState());
      } else {
        System.out.println("Cần ít nhất 2 xu để chọn sản phẩm.");
      }
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
      System.out.println("Vui lòng chọn sản phẩm trước.");
    }
  }

  public static class DispensingState implements State {
    @Override
    public void insertCoin(VendingMachine machine) {
      System.out.println("Đang giao sản phẩm, không thể bỏ xu lúc này.");
    }

    @Override
    public void selectProduct(VendingMachine machine) {
      System.out.println("Đang giao sản phẩm, không thể chọn lại.");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
      System.out.println("Đang giao sản phẩm...");
      machine.resetCoins();
      System.out.println("Sản phẩm đã được giao.");
      machine.setState(new NoCoinState()); // Quay lại trạng thái ban đầu
    }
  }

  public static void main(String[] args) {
    VendingMachine vendingMachine = new VendingMachine();

    // Thử các hành động
    vendingMachine.selectProduct(); // Chưa có xu
    vendingMachine.insertCoin();    // Bỏ 1 xu
    vendingMachine.selectProduct(); // Chưa đủ xu
    vendingMachine.insertCoin();    // Bỏ xu thứ 2
    vendingMachine.selectProduct(); // Chọn sản phẩm
    vendingMachine.dispenseProduct(); // Giao sản phẩm
  }
}
