package juc;

/**
 * @author maple
 * @create 2022-06-04 10:27
 */
public class Demo {
    public static synchronized void add() {
        add();
    }

    public static void main(String[] args) {
        Demo.add();
    }
}
