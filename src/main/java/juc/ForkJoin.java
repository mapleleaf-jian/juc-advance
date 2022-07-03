package juc;

import java.util.concurrent.*;

/**
 * @author maple
 * @create 2022-06-11 10:31
 */
class MyTask extends RecursiveTask<Integer> {
    private static final Integer VALUE = 10;
    private final Integer start; //拆分开始值
    private final Integer end; //拆分结束值
    private Integer result = 0;

    public MyTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 首尾差没超过10，直接相加
        if ((end - start) <= VALUE) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
        } else {
            // 切分
            int middle = start + (end - start) / 2;
            MyTask taskLeft = new MyTask(start, middle);
            MyTask taskRight = new MyTask(middle + 1, end);
            // 子任务分别执行
            taskLeft.fork();
            taskRight.fork();
            // 合并
            result = taskLeft.join() + taskRight.join();
        }
        return result;
    }
}

public class ForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        MyTask myTask = new MyTask(1, 100);
        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并后的结果
        Integer result = forkJoinTask.get();
        System.out.println("result = " + result);
        // 关闭池对象
        forkJoinPool.shutdown();
    }
}
