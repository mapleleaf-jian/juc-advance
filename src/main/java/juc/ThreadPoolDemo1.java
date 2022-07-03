package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maple
 * @create 2022-06-09 7:59
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 创建一池多线程
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            // 执行操作
            threadPool.execute(() -> {
                for (int i = 1; i <= 120; i++) {
                    System.out.println(Thread.currentThread().getName() + "正在处理任务");
                }
            });
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}
