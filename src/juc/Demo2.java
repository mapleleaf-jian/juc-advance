package juc;

/**
 * @author maple
 * @create 2022-06-04 11:24
 */
public class Demo2 {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + ": 外层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + ": 中层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + ": 内层");
                    }
                }
            }
        }).start();
    }
}
