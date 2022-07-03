package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 21:24
 */
public class Demo5 {
    public static void main(String[] args) {
        CompletableFuture<Integer> res = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " com in 1");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " com in 1");
            return 20;
        }), Integer::sum);
        System.out.println(res.join());
    }

    private static void extracted() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " com in 1");
            return 10;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " com in 1");
            return 20;
        });
        // 合并
        CompletableFuture<Integer> res = cf1.thenCombine(cf2, (x, y) -> x + y);
        System.out.println(res.join());
    }
}
