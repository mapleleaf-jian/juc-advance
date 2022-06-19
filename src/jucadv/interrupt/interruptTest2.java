package jucadv.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author maple
 * @create 2022-06-19 16:32
 */
public class interruptTest2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("中断标志位：" + Thread.currentThread().isInterrupted() + "程序停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("hello ----- interrupt");
            }
        }, "t1");
        t1.start();
        try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> t1.interrupt(), "t2").start();
    }
}
