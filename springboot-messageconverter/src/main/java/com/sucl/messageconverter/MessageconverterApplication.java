package com.sucl.messageconverter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Slf4j
@SpringBootApplication
public class MessageconverterApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(MessageconverterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, HandlerMapping> handlerMappingMap = applicationContext.getBeansOfType(HandlerMapping.class);
        handlerMappingMap.values().forEach(mc -> {
            log.info(" HandlerMapping : " + mc.toString());
        });

        Map<String, HandlerMethodReturnValueHandler> handlerMethodReturnValueHandlerMap = applicationContext.getBeansOfType(HandlerMethodReturnValueHandler.class);
        handlerMethodReturnValueHandlerMap.values().forEach(hv -> {
            log.info(" handlerMethodReturnValueHandlerMap : " + hv.toString());
        });

        Map<String, HttpMessageConverter> messageConverterMap = applicationContext.getBeansOfType(HttpMessageConverter.class);
        messageConverterMap.values().forEach(mc -> {
            log.info(" HttpMessageConverter : " + mc.toString());
        });

    }

}
