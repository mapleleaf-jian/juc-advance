package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author maple
 * @create 2022-06-04 18:46
 */
public class FTTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft1 = new FutureTask<>(() -> "ft1");// lambda表达式创建Callable接口的匿名实现类
        FutureTask<String> ft2 = new FutureTask<>(() -> "ft2");
        new Thread(ft1, "thread1").start();
        new Thread(ft2, "thread2").start();
        System.out.println(ft2.get());
        System.out.println(ft1.get());
    }
}
