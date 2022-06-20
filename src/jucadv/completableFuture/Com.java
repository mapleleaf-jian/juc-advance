package jucadv.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maple
 * @create 2022-06-15 21:45
 */
public class Com {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // ForkJoinPool.commonPool-worker-1 (默认线程池)
        });
        System.out.println(completableFuture1.get()); // null

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // pool-1-thread-1 (自定义线程池)
        }, threadPool);
        System.out.println(completableFuture2.get()); // null
        threadPool.shutdown();
    }
}
