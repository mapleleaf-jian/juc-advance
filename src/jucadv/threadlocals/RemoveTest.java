package jucadv.threadlocals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author maple
 * @create 2022-06-30 21:43
 */
class MyNum {
    public ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    public void add() {
        threadLocal.set(1 + threadLocal.get());
    }
}
public class RemoveTest {
    public static void main(String[] args) {
        MyNum myNum = new MyNum();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before = myNum.threadLocal.get();
                        myNum.add();
                        Integer after = myNum.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() + " before: " + before + " after: " + after);
                    } finally {
                        myNum.threadLocal.remove();
                    }
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
