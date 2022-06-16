package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-08 7:38
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        new Thread(() -> {
            for (int i = 1; i <= 4; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + "放元素: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "生产者").start();
        new Thread(() -> {
            for (int i = 1; i <= 4; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + "取元素: " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "消费者").start();
    }
}
