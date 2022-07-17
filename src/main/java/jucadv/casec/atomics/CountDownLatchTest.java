package jucadv.casec.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zcl2806
 * @create 2022-06-24 11:34
 */
class MyNum {
    public AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlus() {
        atomicInteger.getAndIncrement();
    }
}
public class CountDownLatchTest {
    public static final int SIZE = 50;
    public static void main(String[] args) throws InterruptedException {
        MyNum myNum = new MyNum();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNum.addPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(myNum.atomicInteger.get()); // 50000
    }
}
