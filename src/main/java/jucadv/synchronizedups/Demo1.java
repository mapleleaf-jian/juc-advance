package jucadv.synchronizedups;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author maple
 * @create 2022-07-09 21:25
 */
public class Demo1 {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println("10进制: " + o.hashCode());
        System.out.println("16进制: " + Integer.toHexString(o.hashCode()));
        System.out.println("2进制: " + Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
