package jucadv.synchronizedups;

/**
 * @author maple
 * @create 2022-07-10 15:55
 */
public class LockClearDemo {
    public static Object obj = new Object();
    public static void m1() {
        Object o = new Object();
        synchronized (o) {
            System.out.println("进入锁" + o.hashCode() + "\t" + obj.hashCode());
        }
    }
    public static void main(String[] args) {
        LockClearDemo lockClearDemo = new LockClearDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lockClearDemo.m1();
            }, String.valueOf(i + 1)).start();
        }
    }
}
