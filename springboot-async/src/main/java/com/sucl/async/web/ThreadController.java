package com.sucl.async.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sucl
 * @since 2019/9/11
 */
@RestController
@RequestMapping
public class ThreadController {

    public ExecutorService executorService = Executors.newFixedThreadPool(3);

    @GetMapping("/{i}")
    public String run(@PathVariable("i") int i){
        List<Callable<Object>> callables = new ArrayList<>();

        List<Object> count = new ArrayList<>();
        for(int x=0;x<i;x++){
            callables.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    TimeUnit.SECONDS.sleep(2);
                    long time = System.currentTimeMillis();
                    if(count.size()>5){
                        throw new RuntimeException("出现异常");
                    }
                    System.out.println("执行任务："+time);
                    count.add(time);
                    return time;
                }
            });
        }
        try {
            List<Future<Object>> futures = executorService.invokeAll(callables);
            for(Future future : futures){
                Object val = future.get();
                System.out.println("val:" + val);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        while (true){
            if(executorService.isTerminated()){
                System.out.println("任务执行完成！");
                break;
            }
        }

        return "ok";
    }



}
