package jucadv.threadlocals;

/**
 * @author zcl2806
 * @create 2022-07-01 15:41
 */
public class Demo {
    public static void main(String[] args) {

    }

    public void func1() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(25);
        System.out.println(threadLocal.get());
    }
}
