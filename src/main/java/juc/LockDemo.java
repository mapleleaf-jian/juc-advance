package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maple
 * @create 2022-06-04 15:21
 */
public class LockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            lock.unlock();
        }, "t2").start();
    }
}
