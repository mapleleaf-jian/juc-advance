package jucadv.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-19 16:32
 */
public class interruptTest3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());//main false
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());//false
        Thread.currentThread().interrupt(); // 中断标志位设置为true
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());// true
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());//false
    }
}
