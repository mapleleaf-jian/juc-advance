package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author maple
 * @create 2022-06-04 19:14
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 初始化 CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                // 计数 -1
                countDownLatch.countDown(); // 在每个线程中执行 -1 的操作
            }, String.valueOf(i)).start();
        }
        // 等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "教室门上锁！");
    }
}
