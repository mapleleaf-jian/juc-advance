package jucadv.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author zcl2806
 * @create 2022-06-24 13:51
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray1 = new AtomicIntegerArray(5);
        AtomicIntegerArray atomicIntegerArray2 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray atomicIntegerArray3 = new AtomicIntegerArray(new int[]{1 ,2, 3});
        System.out.println(atomicIntegerArray1);
    }
}
