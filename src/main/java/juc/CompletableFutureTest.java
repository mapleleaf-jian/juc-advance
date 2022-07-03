package juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author maple
 * @create 2022-06-11 11:17
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步回调，没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
        });
        // 异步回调，有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
            return 1024;
        });
        // 处理返回值
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("t = " + t);
            System.out.println("u = " + u);
        }).get();

    }
}
