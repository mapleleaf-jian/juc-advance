package jucadv.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author maple
 * @create 2022-07-03 20:16
 */
public class JOLDemo {
    public static void main(String[] args) {
        // 展示VM的细节详细情况
//        System.out.println(VM.current().details());
//
//        System.out.println(VM.current().objectAlignment()); // 8

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer {
    int age;
    boolean flag;
}