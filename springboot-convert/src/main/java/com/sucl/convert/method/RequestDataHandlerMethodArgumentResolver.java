package com.sucl.convert.method;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author sucl
 * @since 2019/8/1
 */
public class RequestDataHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> paramClazz = parameter.getParameterType();
        Object param = BeanUtils.instantiate(paramClazz);

        Iterator<String> reqParams = webRequest.getParameterNames();
        Map<String, String> paramMap = new HashMap<>();
        while (reqParams.hasNext()) {
            String propKey = reqParams.next();
            paramMap.put(propKey, webRequest.getParameter(propKey));
        }

        BeanInfo beanInfo = Introspector.getBeanInfo(paramClazz);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String prop = pd.getName();
            Class<?> propType = pd.getPropertyType();
            Method setMethod = pd.getWriteMethod();
            if (setMethod != null && propType == String.class)
                setMethod.invoke(param, paramMap.get(prop));
        }
        return param;
    }

    private class ParamFieldCallback implements ReflectionUtils.FieldCallback {
        private List<String> properties = new ArrayList<>();
        private List<Class> propTypes = new ArrayList<>();

        @Override
        public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
            properties.add(field.getName());
            propTypes.add(field.getType());
        }

        public List<String> getProperties() {
            return properties;
        }

        public List<Class> getPropTypes() {
            return propTypes;
        }
    }
}
