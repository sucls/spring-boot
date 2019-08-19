package com.sucl.configure.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @author sucl
 * @since 2019/8/19
 */
@Conditional({_ConditionBean._Condition.class})
@Component
public class _ConditionBean {

    public _ConditionBean(){
        System.out.println("construct _ConditionBean");
    }

    public static class _Condition implements Condition{

        /**
         * 定义该对应bean能够被注册的条件
         * @param context
         * @param metadata
         * @return
         */
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Environment env = context.getEnvironment();
            Boolean cb = env.getProperty("conditionBean", Boolean.class);
            return cb==null?false:cb;
        }
    }
}
