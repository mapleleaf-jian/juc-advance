package jucadv.reens;

import java.util.concurrent.locks.StampedLock;

/**
 * @author maple
 * @create 2022-07-31 20:32
 */
public class Test2 {
    public static int number = 10;
    public static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamped = stampedLock.writeLock();
        try {
            number++;
        } finally {
            stampedLock.unlockWrite(stamped);
        }
    }

    public void read() {
        long stamped = stampedLock.readLock();
        try {
            System.out.println("number = " + number);
        } finally {
            stampedLock.unlockRead(stamped);
        }
    }

    public static void main(String[] args) {

    }
}
