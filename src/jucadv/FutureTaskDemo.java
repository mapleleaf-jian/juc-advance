package jucadv;

import java.util.concurrent.*;

/**
 * @author maple
 * @create 2022-06-12 20:51
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        long startTime = System.currentTimeMillis();
        FutureTask<String> ft1 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            return "task1 over";
        });
        threadPool.submit(ft1);

        FutureTask<String> ft2 = new FutureTask<>(() -> {
            try {TimeUnit.MILLISECONDS.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
            return "task1 over";
        });
        threadPool.submit(ft2);

        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println(ft1.get());
        System.out.println(ft2.get());
        long endTime = System.currentTimeMillis();
        System.out.println("总耗费时间: " + (endTime - startTime) + "毫秒"); // 505
        threadPool.shutdown();
    }

    public static void fun() {
        long startTime = System.currentTimeMillis();
        try {TimeUnit.MILLISECONDS.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
        try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("总耗费时间: " + (endTime - startTime) + "毫秒"); // 1237
    }
}
