package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author maple
 * @create 2022-06-04 18:46
 */
public class FTTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft1 = new FutureTask<>(() -> "ft1");
        new Thread(ft1, "thread1").start();
        while (!ft1.isDone()) {
            System.out.println("wait..");
        }
        System.out.println(ft1.get());
        System.out.println(ft1.get());
        System.out.println("is done");
    }
}
