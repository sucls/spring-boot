package com.sucl.log.config;

import com.sucl.log.inspect.logback.Logback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sucl
 * @since 2019/9/6
 */
@Configuration
public class LogConfiguration {


    @Bean(initMethod = "init")
    public Logback logback(){
        return new Logback();
    }
}
