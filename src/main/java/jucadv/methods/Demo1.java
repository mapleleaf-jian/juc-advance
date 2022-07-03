package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 15:34
 */
public class Demo1 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });
        System.out.println(completableFuture.getNow("xxx"));
        System.out.println(completableFuture.complete("yyy") + "\t" + completableFuture.join()); // true yyy
    }
}
