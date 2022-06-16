package juc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author maple
 * @create 2022-06-05 10:42
 */
public class jiang {
    /*public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();

        writeLock.lock(); // 获取写锁
        System.out.println("获取写锁");
        readLock.lock(); // 获取读锁
        System.out.println("获取读锁");

        writeLock.unlock();
        readLock.unlock();
    }*/

    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();

        readLock.lock(); // 获取读锁
        System.out.println("获取读锁");

        writeLock.lock(); // 获取写锁
        System.out.println("获取写锁");

        writeLock.unlock();
        readLock.unlock();
    }
}
