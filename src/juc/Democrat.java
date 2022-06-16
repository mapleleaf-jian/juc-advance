package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maple
 * @create 2022-06-04 15:16
 */
public class Democrat {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + ": 外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + ": 内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
