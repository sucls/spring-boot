package com.sucl.configure;

import com.sucl.configure.service.AccountService;
import com.sucl.configure.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author sucl
 * @since 2019/8/19
 */
@Configuration
@EnableConfigurationProperties(ModelProperties.class)
public class ModelConfiguration {

    @Value("${model.id}")
    private String id;

    private final ModelProperties properties;

    public ModelConfiguration(ModelProperties properties){
        this.properties = properties;
    }

    @PostConstruct
    public void init(){
        System.out.println(id);
        System.out.println(properties.toString());
    }

    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }
}
