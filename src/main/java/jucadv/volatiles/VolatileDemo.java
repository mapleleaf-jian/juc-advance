package jucadv.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author zcl2806
 * @create 2022-06-21 5:03 PM
 */
public class VolatileDemo {
    static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
        }, "t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { throw new RuntimeException(e); }
        flag = false;
        System.out.println(Thread.currentThread().getName() + " 修改了flag：" + flag);
    }
}
