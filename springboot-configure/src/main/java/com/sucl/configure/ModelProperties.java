package com.sucl.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 默认从classpath:application.properties中读取，通过@PropertySource指定配置文件
 * 使用：
 *  1、@PropertySource + @ConfigurationProperties + @Configuration（@Component/../@Bean）
 *  2、@ConfigurationProperties + @EnableConfigurationProperties
 *
 *  @PropertySource 是全局注册
 *
 * @author sucl
 * @since 2019/8/19
 */
@Data
//@Configuration
//@PropertySource(value = "classpath:config/model.properties",ignoreResourceNotFound = true,encoding = "utf-8")
@ConfigurationProperties(prefix = "model")
public class ModelProperties {

    private String id;

    private String name;

}
