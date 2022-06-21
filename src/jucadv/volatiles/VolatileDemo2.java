package jucadv.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @author maple
 * @create 2022-06-21 20:57
 */
class Num {
    volatile Integer num = 0;
    public void addPlus() {
        num++;
    }
}
public class VolatileDemo2 {
    public static void main(String[] args) {
        Num num = new Num();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    num.addPlus();
                }
            }, String.valueOf(i)).start();
        }
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(num.num);
    }
}
