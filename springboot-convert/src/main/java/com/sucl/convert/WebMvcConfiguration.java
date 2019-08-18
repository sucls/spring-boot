package com.sucl.convert;

import com.sucl.convert.convert.StringToDateConverterFactory;
import com.sucl.convert.convert.StringToDateGenericConverter;
import com.sucl.convert.method.RequestDataHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.List;

/**
 * @author sucl
 * @since 2019/7/26
 */
//@EnableWebMvc
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToDateConverterFactory());
//        registry.addConverter(new StringToDateGenericConverter(String.class, new StringToDateGenericConverter.DateParser(),new StringToDateGenericConverter.StringDateConversionService()));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestDataHandlerMethodArgumentResolver());
    }
}
