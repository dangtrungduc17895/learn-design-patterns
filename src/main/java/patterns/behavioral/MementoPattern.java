package patterns.behavioral;
/*
- Memento: lưu dữ data của 1 đối tượng tại 1 specific moment
- Originator: Đối tượng giữ data hiện tại, can save and restore Memento
- Caretaker: Stack lưu trữ các phiên bản Memento, khi cần restore sẽ lấy từ đây
Flow: Originator sử dụng Memento như 1 record chứa data. Dùng Caretaker để lưu các phiên bản Memento
khi cần restore sẽ lấy trong Caretaker, Caretaker dùng stack để đảm bảo LIFO.
 */
public class MementoPattern {

  // Lớp Memento: Lưu trữ trạng thái
  public static class EditorMemento {
    private final String text; // Trạng thái được lưu

    public EditorMemento(String text) {
      this.text = text;
    }

    public String getSavedText() {
      return text;
    }
  }

  // Originator: Trình soạn thảo văn bản
  public static class TextEditor {
    private String text;

    public TextEditor() {
      this.text = "";
    }

    public void write(String newText) {
      this.text = newText;
      System.out.println("Nội dung hiện tại: " + text);
    }

    // Tạo Memento để lưu trạng thái
    public EditorMemento save() {
      return new EditorMemento(text);
    }

    // Khôi phục trạng thái từ Memento
    public void restore(EditorMemento memento) {
      this.text = memento.getSavedText();
      System.out.println("Đã khôi phục: " + text);
    }

    public String getText() {
      return text;
    }
  }

  // Caretaker: Quản lý lịch sử trạng thái
  public static class History {
    private java.util.Stack<EditorMemento> mementos = new java.util.Stack<>();

    public void saveState(EditorMemento memento) {
      mementos.push(memento);
      System.out.println("Đã lưu trạng thái.");
    }

    public EditorMemento undo() {
      if (!mementos.isEmpty()) {
        return mementos.pop();
      }
      System.out.println("Không có trạng thái nào để khôi phục.");
      return null;
    }
  }

  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    History history = new History();

    // Viết và lưu trạng thái
    editor.write("Xin chào");
    history.saveState(editor.save());

    editor.write("Xin chào, bạn khỏe không?");
    history.saveState(editor.save());

    editor.write("Xin chào, bạn khỏe không? Hôm nay là thứ Ba.");
    System.out.println("Nội dung hiện tại - main: " + editor.getText());

    // Khôi phục trạng thái trước đó
    editor.restore(history.undo());
    editor.restore(history.undo());
  }
}
