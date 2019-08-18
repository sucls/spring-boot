package com.sucl.convert.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换成同一种类型的转换
 * @author sucl
 * @date 2019/5/29
 */
//方法④
@Slf4j
public class StringToDateConverterFactory implements ConverterFactory<String,Date> {

    @Override
    public <T extends Date> Converter<String,T> getConverter(Class<T> clazz) {

        return new StringToDate<T>(clazz);
    }

    private static class StringToDate<T extends Date> implements Converter<String,T> {
        private Class<T> targetType;

        public StringToDate(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = sdf.parse(source);
                return (T) date;
            } catch (ParseException e) {
                log.error("",e);
            }
            return null;
        }
    }
}
