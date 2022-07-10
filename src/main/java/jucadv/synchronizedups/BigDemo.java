package jucadv.synchronizedups;

/**
 * @author maple
 * @create 2022-07-10 16:17
 */
public class BigDemo {
    static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("111");
            }
            synchronized (obj) {
                System.out.println("222");
            }
            synchronized (obj) {
                System.out.println("333");
            }

            synchronized (obj) {
                System.out.println("111");
                System.out.println("222");
                System.out.println("333");
            }
        }).start();
    }
}
