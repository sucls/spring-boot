package com.sucl.async;

import com.sucl.async.service.AsyncService;
import com.sucl.async.service.MyAsyncConfigurer;
import com.sucl.async.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AsyncConfigurer
 *
 */
@Slf4j
@RestController
@RequestMapping("/async")
@SpringBootApplication
@EnableAsync
public class SpringbootAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAsyncApplication.class, args);
    }

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(10);
        executor.setMaxPoolSize(15);
        executor.setKeepAliveSeconds(1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.setThreadNamePrefix("thread-taskExecutor-");
        executor.initialize();
        return executor;
    }


    @Autowired
    private AsyncService asyncService;
    @Autowired
    private OtherService otherService;

    /**
     * 根据上面ThreadPoolTaskExecutor的配置
     * 当同时处理 线程超过25时，则抛出异常
     *
     * ②有返回的方法可以通过try cache处理异常
     *
     * @param c
     */
    @GetMapping("/{c}")
    public void async(@PathVariable int c) throws ExecutionException, InterruptedException {
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < c; i++) {
//            otherService.run();
//            asyncService.run();
            futures.add(asyncService.run2());
        }
        for (Future future : futures) {
            try {
                Object f = future.get();
                System.out.println(">>>>>>>>>>>>>>>>>> " + future.get());
            } catch (Exception e) {
                log.error("error："+e.getMessage());
            }
        }
    }

}
