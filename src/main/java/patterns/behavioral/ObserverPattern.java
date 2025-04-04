package patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
- Một Publisher chứa 1 list Observer(subscriber)
- Khi có thay đổi ở Publisher, sẽ thông báo đến toàn bộ list Observer
- Observer sẽ chứa 1 method update. Khi có thay đổi cần dc thông báo đến các Observer,
Publisher sẽ trigger gọi method kia
 */
  public class ObserverPattern {
  public interface Observer {
    void update(Notification notification);
  }

  public static class Publisher {
    List<Observer> observers = new ArrayList<>();
    List<Notification> notifications = new ArrayList<>();

    public void registerObserver(Observer observer) {
      observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
      observers.remove(observer);
    }

    public void notifyObservers(Notification notification) {
      notifications.add(notification);
      sendNotification(notification);
    }

    private void sendNotification(Notification notification) {
      for (Observer observer : observers) {
        observer.update(notification);
      }
    }
  }

  public static class Notification {
    String message;
    Notification(String message) {
      this.message = message;
    }
  }

  public static class SubA implements Observer {

    @Override
    public void update(Notification notification) {
      System.out.println("I am A, I received: " + notification.message);
    }
  }

  public static class SubB implements Observer {

    @Override
    public void update(Notification notification) {
      System.out.println("I am B, I received: " + notification.message);
    }
  }

  public static void main(String[] args){
    Observer A = new SubA();
    Observer B = new SubB();

    Publisher publisher = new Publisher();
    publisher.registerObserver(A);
    publisher.registerObserver(B);
    publisher.notifyObservers(new Notification("Mess1"));

    publisher.unregisterObserver(A);
    publisher.notifyObservers(new Notification("Mess2"));
    publisher.unregisterObserver(B);
    publisher.notifyObservers(new Notification("Mess3"));
  }
}
