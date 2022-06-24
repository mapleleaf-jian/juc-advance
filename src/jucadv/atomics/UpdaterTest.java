package jucadv.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zcl2806
 * @create 2022-06-24 16:27
 */
class Account {
    public String bankName = "BBC";
    public volatile int money = 0;
    public void add() {
        money++;
    }

    AtomicIntegerFieldUpdater fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Account.class, "money");
    public void addPlus(Account account) {
        fieldUpdater.getAndIncrement(account);
    }
}
public class UpdaterTest {
    public static final int Num = 10;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(Num);
        Account account = new Account();
        for (int i = 0; i < Num; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
//                        account.add();
                        account.addPlus(account);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " " + account.money);
    }
}
