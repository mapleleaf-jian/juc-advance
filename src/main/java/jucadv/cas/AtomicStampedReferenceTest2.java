package jucadv.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zcl2806
 * @create 2022-06-24 10:44
 */

public class AtomicStampedReferenceTest2 {
    static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 初始值: " +
                    stampedReference.getReference() + " 初始版本号: " + stamp);
            // 保证线程 t2 也获取到初始值和初始版本号
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { throw new RuntimeException(e); }
            boolean b =
                    stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " 第一次修改 " + b + " 值: " +
                    stampedReference.getReference() + " 版本号: " + stamp);
            boolean c =
                    stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 第二次修改 " + b + " 值: " +
                    stampedReference.getReference() + " 版本号: " + stampedReference.getStamp());
        }, "t1").start();
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 初始值: " +
                    stampedReference.getReference() + " 初始版本号: " + stamp);
            // 保证线程 t1 已经发生了ABAP问题
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { throw new RuntimeException(e); }
            boolean b =
                    stampedReference.compareAndSet(100, 2022, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + " 第一次修改 " + b + " 值: " +
                    stampedReference.getReference() + " 版本号: " + stampedReference.getStamp());
        }, "t2").start();
    }
}
