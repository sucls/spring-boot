package com.sucl.convert.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通过WebMvcConfigurerAdapter注册，并非交由spring容器管理
 * <p>
 * 最简单最直接的转换
 *
 * @author sucl
 * @date 2019/5/29
 */
//方法③
@Slf4j
//@Component
public class StringDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }
}
