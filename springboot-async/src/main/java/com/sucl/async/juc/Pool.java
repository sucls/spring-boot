package com.sucl.async.juc;

/**
 * @author sucl
 * @since 2019/9/30
 */
public class Pool {

    public static class GunFactory{
        private int count;
        private static final int MAX = 10;

        public synchronized void produce(){
            while (count>=MAX){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("新增1,剩余"+count);
            count++;
            notifyAll();
        }

        public synchronized void consume(){
            while (count<=0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("减去1,剩余"+count);
            count--;
            notifyAll();
        }
    }

    public static class Producer extends Thread{
        private GunFactory factory;
        public Producer(GunFactory factory){
            this.factory = factory;
        }

        @Override
        public void run() {
            while (true){
                factory.produce();
            }
        }
    }

    public static class Consumer extends Thread{
        private GunFactory factory;
        public Consumer(GunFactory factory){
            this.factory = factory;
        }

        @Override
        public void run() {
            while (true){
                factory.consume();
            }
        }
    }

    public static void main(String[] args) {
        GunFactory factory = new GunFactory();
        Producer producer = new Producer(factory);
        Consumer consumer = new Consumer(factory);
        producer.start();
        consumer.start();
    }

}
