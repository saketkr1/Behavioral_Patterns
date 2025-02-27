

/*
    * Iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container's elements.
    * The Iterator pattern decouples algorithms from containers.
    * The Iterator pattern allows you to access elements sequentially without exposing the underlying structure of the container.
 */

import java.util.ArrayList;
import java.util.List;

interface IIterator {
    boolean hasNext();
    Object next();
}

class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class BookCollection {
    List<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    Book getBook(int index) {
        return books.get(index);
    }

    IIterator createIterator() {
        return new BookIterator();
    }

    private class BookIterator implements IIterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < books.size();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return books.get(index++);
            }
            return null;
        }
    }

}
public class Iterator {
    public static void main(String[] args) {
        BookCollection bookCollection = new BookCollection();
        bookCollection.addBook(new Book("Design Patterns: Elements of Reusable Object-Oriented Software"));
        bookCollection.addBook(new Book("Clean Code: A Handbook of Agile Software Craftsmanship"));
        bookCollection.addBook(new Book("Refactoring: Improving the Design of Existing Code"));

        IIterator iterator = bookCollection.createIterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getTitle());
        }
    }
}
