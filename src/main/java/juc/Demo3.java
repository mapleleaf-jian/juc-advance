package juc;

/**
 * @author maple
 * @create 2022-06-04 15:49
 */
public class Demo3 {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + ": 持有锁a，试图获取锁b");
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + ": 获取锁b");
                }
            }
        }, "aa").start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + ": 持有锁b，试图获取锁a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + ": 获取锁a");
                }
            }
        }, "bb").start();
    }
}
