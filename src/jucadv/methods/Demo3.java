package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maple
 * @create 2022-06-18 16:27
 */
public class Demo3 {
    public static void main(String[] args) {
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
