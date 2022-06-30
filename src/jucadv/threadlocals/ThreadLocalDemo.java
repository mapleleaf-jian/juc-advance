package jucadv.threadlocals;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zcl2806
 * @create 2022-06-30 17:20
 */
class House {
    int saleCount = 0;
    public synchronized void saleHouse() { // synchronized加锁的方式
        saleCount++;
    }

    ThreadLocal<Integer> saleVol = ThreadLocal.withInitial(() -> 0);
    public void saleByThreadLocal() { // ThreadLocal的方式
        saleVol.set(1 + saleVol.get());
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            int count = new Random().nextInt(5) + 1;
            new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    house.saleHouse();
                    house.saleByThreadLocal();
                }
                System.out.println(Thread.currentThread().getName() + "号卖出 " + count);
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "总共卖出: " + house.saleCount);
    }
}
