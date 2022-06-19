package jucadv.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-19 19:41
 */
public class InterruptDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                System.out.println("-----" + i);
            }
            System.out.println("调用interrupt方法后t1线程的中断标志位02: " + Thread.currentThread().isInterrupted()); // true
        }, "t1");
        t1.start();
        System.out.println("t1线程默认的中断标志位: " + t1.isInterrupted()); // false

        // 暂停2ms后，t1线程还没执行完成，只会修改标志位，并不会中断线程的执行
        try { TimeUnit.MILLISECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        t1.interrupt();
        System.out.println("调用interrupt方法后t1线程的中断标志位01: " + t1.isInterrupted()); // true

        // 暂停2秒后，线程早已经执行完成，此时调用interrupt不会有任何影响
        // 在jdk8中会将标志位自动恢复为false，但在jdk17中不会自动恢复，所以这里仍然为true
        t1.interrupt();
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("调用interrupt方法后t1线程的中断标志位03: " + t1.isInterrupted()); // true
    }
}
