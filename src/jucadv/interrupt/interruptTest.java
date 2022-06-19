package jucadv.interrupt;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author maple
 * @create 2022-06-19 16:32
 */
public class interruptTest {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t interrupt 被修改为true，程序停止");
                    break;
                }
                System.out.println("aa hello interrupt");
            }
        }, "aa");
        t1.start();
        new Thread(() -> {
            t1.interrupt();
        }, "bb").start();
        // 或线程 t1 自己中断
        t1.interrupt();
    }

    private static void extracted1() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean 被修改为true，程序停止");
                    break;
                }
                System.out.println("aa hello atomicBoolean");
            }
        }, "aa").start();
        new Thread(() -> {
            atomicBoolean.set(true);
        }, "bb").start();
    }

    private static void extracted() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop 被修改为true，程序停止");
                    break;
                }
                System.out.println("aa hello volatile");
            }
        }, "aa").start();
        new Thread(() -> {
            isStop = true;
        }, "bb").start();
    }
}
