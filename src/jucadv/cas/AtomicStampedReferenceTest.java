package jucadv.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zcl2806
 * @create 2022-06-23 17:39
 */
@Data
@AllArgsConstructor
class Book {
    String name;
    int price;
}
public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        Book book1 = new Book("book1", 12);
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(book1, 1);
        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());
        Book book2 = new Book("book2", 15);
        boolean b = stampedReference.compareAndSet(book1, book2, 1, stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
        boolean c = stampedReference.compareAndSet(book2, book1, 2, stampedReference.getStamp() + 1);
        System.out.println(c + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}
