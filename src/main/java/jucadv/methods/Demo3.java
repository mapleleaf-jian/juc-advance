package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 16:27
 */
public class Demo3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "执行1号任务");
                return 11;
            }, threadPool).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "执行2号任务");
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "执行3号任务");
            });
            System.out.println(completableFuture.join());
        } finally {
            threadPool.shutdown();
        }
    }

    private static void extracted1() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        System.out.println(CompletableFuture.supplyAsync(() -> 1).thenRun(() -> {}).join()); // null
        System.out.println(CompletableFuture.supplyAsync(() -> 1).thenApply(f -> f + 2).join()); // 3
        System.out.println(CompletableFuture.supplyAsync(() -> 1).thenAccept(System.out::println).join()); // 1  null
        threadPool.shutdown();
    }

    private static void extracted() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        CompletableFuture.supplyAsync(() -> 1).thenApply(f -> f + 2).thenApply(f -> f + 3)
                        .thenAccept(System.out::println); // 6
        threadPool.shutdown();
    }
}
