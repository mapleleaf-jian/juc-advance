package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author maple
 * @create 2022-06-04 18:41
 */
class FTThread1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
public class FTTest1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask<>(new FTThread1());
        new Thread(ft, "ft1").start();
        System.out.println(ft.get());
    }
}
