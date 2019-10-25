package com.sucl.async.juc.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 信号量 -> 认证
 * acquire() 消费认证
 * release() 归还认证
 * 流量控制：
 * @author sucl
 * @since 2019/9/26
 */
public class SemaphoreTest {

    private Semaphore semaphore = new Semaphore(5);

    public void run(){
        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<10;i++){
            threads.add(new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {

                }
                System.out.println("执行线程："+Thread.currentThread().getName());
            },"T-"+i));
        }

        threads.forEach(t->t.start());

        threads.forEach(t->{
            try {
                t.join();
                System.out.println("完成线程:"+t.getName());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        semaphoreTest.run();
    }

}
