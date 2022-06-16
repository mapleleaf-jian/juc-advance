package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-10 22:06
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            // 执行操作
            threadPool.execute(() -> {
                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "正在处理任务");
                }
            });
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}
