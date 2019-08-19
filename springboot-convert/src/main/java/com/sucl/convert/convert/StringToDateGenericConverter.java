package com.sucl.convert.convert;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * @author sucl
 * @date 2019/5/29
 */
public class StringToDateGenericConverter implements GenericConverter {

    //目标类型T
    private Class<?> fieldType;

    //将String -> T
    private Parser parser;

    //
    private ConversionService conversionService;

    public StringToDateGenericConverter(Class<?> fieldType, Parser parser, ConversionService conversionService) {
        this.fieldType = fieldType;
        this.parser = parser;
        this.conversionService = conversionService;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, fieldType));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceTypeDescriptor, TypeDescriptor targetTypeDescriptor) {
        String text = (String) source;
        Object result = null;
        try {
            result = parser.parse(text, LocaleContextHolder.getLocale());//
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (result == null) {
            return null;
        }
        TypeDescriptor resultClass = TypeDescriptor.valueOf(result.getClass());
        if (!resultClass.isAssignableTo(targetTypeDescriptor)) {//源不是目标的子类
            result = conversionService.convert(result, resultClass, targetTypeDescriptor);
        }
        return result;
    }

    public static class DateParser implements Parser<Date> {

        @Override
        public Date parse(String text, Locale locale) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(text);
        }
    }

    public static class StringDateConversionService extends GenericConversionService {

    }
}
