package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author maple
 * @create 2022-06-04 16:48
 */
class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
class MyThread2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }
}
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread1(), "AA").start();
        FutureTask<String> ft = new FutureTask<>(new MyThread2());
        new Thread(ft, "BB").start();
        System.out.println(ft.get());
    }
}
