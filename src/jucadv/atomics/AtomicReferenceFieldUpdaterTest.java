package jucadv.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author zcl2806
 * @create 2022-06-24 17:37
 */
class MyVar {
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<MyVar, Boolean> fieldUpdater =
            AtomicReferenceFieldUpdater.newUpdater(MyVar.class, Boolean.class, "isInit");
    public void init(MyVar myVar) throws InterruptedException {
        if (fieldUpdater.compareAndSet(myVar, false, true)) {
            System.out.println(Thread.currentThread().getName() + "正在初始化");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " 初始化完成");
        } else {
            System.out.println(Thread.currentThread().getName() + " 其他线程正在操作");
        }
    }
}

public class AtomicReferenceFieldUpdaterTest {
    private static final int SIZE = 5;
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {

            }, String.valueOf(i)).start();
        }
    }
}
