package jucadv.casec.atomics;

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
    public void init(MyVar myVar) {
        if (fieldUpdater.compareAndSet(myVar, false, true)) {
            System.out.println(Thread.currentThread().getName() + "正在初始化");
            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " 初始化完成");
        } else {
            // 使没有执行任务的线程阻塞一段时间
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + " 其他线程正在进行初始化");
        }
    }
}

public class AtomicReferenceFieldUpdaterTest {
    private static final int SIZE = 5;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        MyVar myVar = new MyVar();
        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {
                myVar.init(myVar);
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
    }
}
