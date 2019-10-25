package com.sucl.configure;

import com.sucl.configure.service.AccountService;
import com.sucl.configure.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @author sucl
 * @since 2019/8/19
 */
//@Import()
//@ImportResource
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

    @Primary
    @Lazy
    @Bean
    public AccountService accountService(){
        return new AccountServiceImpl();
    }

    /**
     * 内部类
     * 依赖于outter instance
     * ModelConfiguration必须实例化后该类才会被识别
     */
    @Configuration
    public class InnerModelConfiguration{

    }

    /**
     * 静态内部类类
     * 仅依赖于outter class
     * 无需ModelConfiguration实例化该类也会被识别
     */
    @Configuration
    public static class InnserStaticModelConfiguration{

    }
}
