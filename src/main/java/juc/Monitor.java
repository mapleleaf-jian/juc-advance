package juc;

/**
 * @author maple
 * @create 2022-06-12 10:17
 */
public class Monitor {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {

            }
        }, "t1").start();
    }
}
