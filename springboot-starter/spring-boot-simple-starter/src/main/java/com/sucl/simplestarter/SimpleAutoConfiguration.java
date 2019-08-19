package com.sucl.simplestarter;

import com.sucl.simplestarter.service.SimpleService;
import com.sucl.simplestarter.service.SimpleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sucl
 * @since 2019/8/19
 */
@Configuration
public class SimpleAutoConfiguration {

    @Bean
    public SimpleService simpleService(){
        return new SimpleServiceImpl();
    }
}
