package jucadv.synchro;

import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-18 22:08
 */
class Phone {
    public static synchronized void sendEmail() {
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendMS() {
        System.out.println("-----sendMS");
    }

    public void hello() {
        System.out.println("-----hello");
    }
}
public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();
        // 保证线程a先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> {
//            phone.sendMS();
//            phone.hello();
            phone2.sendMS();
        }, "b").start();
    }
}
