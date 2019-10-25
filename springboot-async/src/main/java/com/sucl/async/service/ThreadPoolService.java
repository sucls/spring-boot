package com.sucl.async.service;

import java.util.List;
import java.util.concurrent.*;

/**
 *缓存队列：
 * ArrayBlockingQueue：基于数组的FIFO队列，是有界的，创建时必须指定大小
 * LinkedBlockingQueue： 基于链表的FIFO队列，是无界的，默认大小是 Integer.MAX_VALUE
 * synchronousQueue:一个比较特殊的队列，虽然它是无界的，但它不会保存任务，每一个新增任务的线程必须等待另一个线程取出任务，也可以把它看成容量为0的队列
 * DelayQueue: 延迟队列java.util.concurrent.Delayed 接口
 * PriorityBlockingQueue :可排序的任务
 *
 *拒绝策略：
 * ThreadPoolExecutor.AbortPolicy() 抛出RejectedExecutionException异常
 * ThreadPoolExecutor.CallerRunsPolicy() 由向线程池提交任务的线程来执行该任务
 * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃最旧的任务（最先提交而没有得到执行的任务）
 * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
 *
 * @author sucl
 * @since 2019/9/11
 */
public class ThreadPoolService {

    private ExecutorService executors = Executors.newFixedThreadPool(5);

    /**
     *
     * @param corePoolSize 核心线程数，allowCoreThreadTimeout=true核心线程是否空闲关闭
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 闲时线程保留时常
     * @param unit keepAliveTime时间单位
     * @param workQueue 缓存队列
     * @param threadFactory 线程创建工厂
     * @param handler 决绝策略
     */
    public ThreadPoolService(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {

    }

    public void submit(Callable callable){
        Future feature = executors.submit(callable);
    }

    public void execute(Runnable runnable){
        executors.execute(runnable);
    }

    public void invoke(List<Callable<Object>> tasks){
        try {
            List<Future<Object>> futures = executors.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
