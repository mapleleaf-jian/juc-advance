package jucadv.methods;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 21:17
 */
public class Demo4 {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playA";
        });
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            return "playB";
        });
        CompletableFuture<String> res = cf1.applyToEither(cf2, f -> {
            return f + " is winner";
        });
        System.out.println(Thread.currentThread().getName() + "\t" + res.join()); // main	playA is winner
    }
}
