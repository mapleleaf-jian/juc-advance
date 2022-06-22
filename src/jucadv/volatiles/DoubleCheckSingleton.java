package jucadv.volatiles;

/**
 * @author zcl2806
 * @create 2022-06-22 11:17 AM
 */
public class DoubleCheckSingleton {
    //通过volatile声明，实现线程安全的延迟初始化
    private volatile static DoubleCheckSingleton singleton;
    private DoubleCheckSingleton() { // 私有化构造方法

    }
    public DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            // 多线程并发创建对象时，会加锁，保证只有一个线程在创建对象
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    // 安全隐患：多线程环境下，由于重排序，可能对象还未完成初始化，就被其他线程读取
                    // 解决：利用volatile，禁止 初始化对象 和 设置singleton指向内存空间 的重排序
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        // 对象创建完毕，使用getInstance获取对象不需要加锁
        return singleton;
    }
}
