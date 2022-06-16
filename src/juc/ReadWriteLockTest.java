package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author maple
 * @create 2022-06-05 9:59
 */
// 资源类
class MyCache {
    private final Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(String key, Object val) {
        // 写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写..");
            map.put(key, val);
            System.out.println(Thread.currentThread().getName() + " 写完了..");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
    public Object get(String key) {
        // 读锁
        rwLock.readLock().lock();
        Object res = new Object();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读..");
            res = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读完了..");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
        return res;
    }
}
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(String.valueOf(finalI), finalI);
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(String.valueOf(finalI));
            }, String.valueOf(i)).start();
        }
    }
}
