package jucadv;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maple
 * @create 2022-06-16 19:17
 */
public class Com11 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // ForkJoinPool.commonPool-worker-1 (默认线程池)
            return "hello";
        });
        System.out.println(completableFuture1.get()); // hello

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // pool-1-thread-1 (自定义线程池)
            return "hi";
        }, threadPool);
        System.out.println(completableFuture2.get()); // hi
        threadPool.shutdown();
    }
}
