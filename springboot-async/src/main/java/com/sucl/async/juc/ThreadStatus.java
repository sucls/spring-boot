package com.sucl.async.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author sucl
 * @since 2019/9/30
 */
public class ThreadStatus {

    static{
        /**
         *  新建，new Thread()，此时的状态
         */
        Thread.State _new = Thread.State.NEW;//0
        /**
         *
         */
        Thread.State _runnable = Thread.State.RUNNABLE;//1
        /**
         *
         */
        Thread.State _blocked = Thread.State.BLOCKED;//2
        /**
         *
         */
        Thread.State _waiting = Thread.State.WAITING;//3
        /**
         *
         */
        Thread.State _terminated = Thread.State.TERMINATED;//5
    }

    public static void _new(){
        Thread thread = new Thread();
        System.out.println("线程状态 " + thread.getState()); //NEW
    }

    public static void _runnable(){
        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("线程状态 " + thread.getState()); //RUNNABLE
    }

    /**
     * 一般出现资源竞争时出现，比如synchronized
     * 状态会由new -> runnable -> bloced
     */
    public static void _blocked(){
        Object monitor = new Object();
        Thread thread0 = new Thread(()->{
            synchronized (monitor){
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread0.start();

        Thread thread = new Thread(()->{
            synchronized (monitor){
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
        while (true){
            System.out.println("线程状态 " + thread.getState()); //BLOCKED
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 线程等待状态一般因为 wait()、join()、LockSupport.park()
     * wait()必须先拿到竞争锁，否则IllegalMonitorStateException
     */
    public static void _waitting(){
        Thread thread0 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread0.start();

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
//                    thread0.join();
                    new ThreadStatus().doWaitting();
//                    LockSupport.park();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
//        while (true)
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程状态 " + thread.getState()); //BLOCKED
    }

    public synchronized void doWaitting(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程完成
     */
    public static void _terminated(){
        Thread thread = new Thread();
        thread.start();
        while (thread.isAlive()){    }
        System.out.println("线程状态 " + thread.getState());//TERMINATED
    }

    public static void main(String[] args) {
//        _blocked();

        _waitting();

    }
}
