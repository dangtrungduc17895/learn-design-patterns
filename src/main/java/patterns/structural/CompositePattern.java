package patterns.structural;

import java.util.ArrayList;
import java.util.List;

/*
Đơn giản hóa: Xử lý các đối tượng đơn lẻ và tập hợp như nhau.

Linh hoạt: Dễ dàng thêm hoặc bớt thành phần trong cấu trúc cây.

Tái sử dụng: Có thể áp dụng cho bất kỳ cấu trúc phân cấp nào (file system, tổ chức công ty, menu, v.v.).
*/
public class CompositePattern {

  public interface FileComponent {
    void showInformation();

    int getSize();
  }

  public static class File implements FileComponent {

    private String fileName;
    private int size;

    public File(String fileName, int size) {
      this.fileName = fileName;
      this.size = size;
    }

    @Override
    public void showInformation() {
      System.out.println("File Name: " + fileName);
    }

    @Override
    public int getSize() {
      return size;
    }
  }

  public static class Folder implements FileComponent {

    private String folderName;
    private List<FileComponent> children = new ArrayList<>();

    public Folder(String folderName) {
      this.folderName = folderName;
    }

    public void add(FileComponent file) {
      children.add(file);
    }

    public void remove(FileComponent file) {
      children.remove(file);
    }

    @Override
    public void showInformation() {
      System.out.println("Folder Name: " + folderName);
      for (FileComponent child : children) {
        child.showInformation();
      }
    }

    @Override
    public int getSize() {
      int size = 0;
      for (FileComponent child : children) {
        size += child.getSize();
      }
      return size;
    }
  }

  public static void main(String[] args) {
    FileComponent file1 = new File("file1.txt", 1);
    FileComponent file2 = new File("file2.txt", 2);

    Folder folder = new Folder("fold1");
    folder.add(file1);
    folder.add(file2);

    folder.showInformation();
    System.out.println("Total Size: " + folder.getSize());
  }
}
