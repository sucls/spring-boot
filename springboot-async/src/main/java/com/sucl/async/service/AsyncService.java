package com.sucl.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 两个方法，一个没有返回，一个有返回
 * @author sucl
 * @since 2019/9/26
 */
@Component
public class AsyncService {

    /**
     * 我们可以指定具体的TaskExecutor，否则会从容器中取
     */
    @Async
    public void run(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行任务:"+Thread.currentThread().getName());
    }

    @Async("taskExecutor")
    public Future<String> run2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(System.currentTimeMillis()%2==0){
//            throw new RuntimeException("异常");
        }
        System.out.println("执行任务:"+Thread.currentThread().getName());
        return new AsyncResult( UUID.randomUUID().toString() );
    }


}
