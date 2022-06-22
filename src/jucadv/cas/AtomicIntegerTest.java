package jucadv.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zcl2806
 * @create 2022-06-22 2:48 PM
 */
public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);
        System.out.println(integer.compareAndSet(5, 7) + "\t" + integer.get());
    }
}
