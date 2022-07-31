package jucadv.reens;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author maple
 * @create 2022-07-31 16:43
 */
public class Test1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        writeLock.lock();
        System.out.println("正在写入...");

        readLock.lock();
        System.out.println("正在读取...");
        writeLock.unlock();
        readLock.unlock();
    }
}
