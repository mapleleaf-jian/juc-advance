package juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author maple
 * @create 2022-06-04 19:52
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 1. 创建信号灯，设置许可数量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 2. 获取许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 号车抢到了停车位");
                    // 设置随机停车时间
                    int time = new Random().nextInt(1000);
                    Thread.sleep(time);
                    System.out.println(time + "毫秒后，" + Thread.currentThread().getName() + " 号车离开了停车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // 3. 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
