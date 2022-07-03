package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author maple
 * @create 2022-06-04 19:36
 */
public class CyclicBarrierTest {
    private static final int NUMBER = 5;
    public static void main(String[] args) {
        // 1. 定义循环栅栏，并指定初始值
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("达成了目标！");
        });
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号已收集");
                try {
                    // 2. 等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
