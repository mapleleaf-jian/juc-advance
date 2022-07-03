package jucadv.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author maple
 * @create 2022-06-20 21:07
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "---come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---被唤醒");
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "---发出通知");
        }, "t2").start();
    }

    private static void extracted() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "---come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "---被唤醒");
        }, "t1");
        t1.start();
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "---发出通知");
        }, "t2").start();
    }
}
