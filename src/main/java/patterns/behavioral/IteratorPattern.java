package patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

/*
Tạo 1 interface để giúp duyệt qua từng phần tử của 1 Collection
 */
public class IteratorPattern {
    public interface Iterator<T> {
        boolean hasNext();
        T next();
    }

    public interface IterableCollection<T> {
        Iterator<T> createIterator();
    }

    public static class ListIterator<T> implements Iterator<T> {
        private List<T> items;
        private int index = 0;

        public ListIterator(List<T> items) {
            this.items = items;
        }

        @Override
        public boolean hasNext() {
            return index < items.size();
        }

        @Override
        public T next() {
            return hasNext() ? items.get(index++) : null;
        }
    }

    public static class MyList<T> implements IterableCollection<T> {
        private List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        @Override
        public Iterator<T> createIterator() {
            return new ListIterator<>(items);
        }
    }

    public static void main(String[] args){
        MyList<String> myList = new MyList<>();
        myList.add("A");
        myList.add("B");
        myList.add("C");

        Iterator<String> iterator = myList.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
