package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 15:34
 */
public class Demo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("111");
            return 1;
        }, threadPool).handle((f, e) -> {
            int[] a = new int[5];
            a[5] = 4;
            System.out.println("222");
            return f + 2;
                })
            .handle((f, e) -> {
                System.out.println("333");
                return f + 3;
            })
            .whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("计算结果: " + v); // 6
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                return 0;
            });
        threadPool.shutdown();
    }

    private static void extracted() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace(); }
            return 1;
        }, threadPool).thenApply(f -> f + 2)
                       .thenApply(f -> f + 3)
                       .whenComplete((v, e) -> {
                           if (e == null) {
                               System.out.println("计算结果: " + v); // 6
                           }
                       }).exceptionally(e -> {
                            e.printStackTrace();
                            return 0;
                          });
        threadPool.shutdown();
    }
}
