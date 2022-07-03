package jucadv.atomics;


import javax.management.StringValueExp;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author maple
 * @create 2022-06-25 22:28
 */
class Click {
    long number = 0;
    public synchronized void addBySynchronized() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong();
    public void addByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();
    public void addByLongAdder() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
    public void addByLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}
public class ClickDemo {
    public static final int NUM = 50;
    public static final int _1W = 10000;
    public static void main(String[] args) throws InterruptedException {
        Click click = new Click();
        CountDownLatch countDownLatch1 = new CountDownLatch(NUM);
        CountDownLatch countDownLatch2 = new CountDownLatch(NUM);
        CountDownLatch countDownLatch3 = new CountDownLatch(NUM);
        CountDownLatch countDownLatch4 = new CountDownLatch(NUM);
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        click.addBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费的时间: " + (endTime - startTime) + "ms, \t by synchronized, result = " + click.number);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        click.addByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费的时间: " + (endTime - startTime) + "ms, \t by AtomicLong, result = " + click.atomicLong.get());

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        click.addByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费的时间: " + (endTime - startTime) + "ms, \t by LongAdder, result = " + click.longAdder.sum());

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100 * _1W; j++) {
                        click.addByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("花费的时间: " + (endTime - startTime) + "ms, \t by LongAccumulator, result = " + click.longAccumulator.get());
    }
}
