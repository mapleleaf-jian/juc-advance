package jucadv;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-12 21:47
 */
public class FutureTaskDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "come in...");
            try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
            return "ft over";
        });
        new Thread(ft, "t1").start();
        System.out.println(Thread.currentThread().getName() + "handle other tasks.");
        while (true) {
            if (ft.isDone()) {
                System.out.println(ft.get());
                break;
            } else {
                // 每隔300毫秒询问一次
                try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("The task is handling.");
            }
        }
    }
}
