package jucadv.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author maple
 * @create 2022-06-25 17:51
 */
public class AdderAndAccumulatorDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder(); // 0
        longAdder.increment(); // 1
        longAdder.increment(); // 2
        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 5); // 5
        longAccumulator.accumulate(3); // 8
        longAccumulator.accumulate(4); // 12
        System.out.println(longAccumulator.get());
    }

}
