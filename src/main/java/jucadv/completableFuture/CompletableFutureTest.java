package jucadv.completableFuture;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author maple
 * @create 2022-06-16 22:13
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3); // 使用自定义线程池
        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "come in...");
                int res = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----1秒后出结果----" + res);
                return res;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("计算结果为:  " + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "进行其他工作");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

    }

    private static void extracted() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "come in...");
            int res = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----1秒后出结果----" + res);
            return res;
        });
        System.out.println(Thread.currentThread().getName() + "进行其他工作");
        System.out.println(completableFuture.get());
    }
}
