package com.sucl.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Collection<Callable<Object>> callables = new ArrayList<>();
        for(int i=0;i<100;i++){
            callables.add(new Callable() {
                @Override
                public Object call() throws Exception {
                    System.out.println("执行任务");
                    return null;
                }
            });
        }
        executorService.invokeAll(callables);

        System.out.println("完成");

    }

}
