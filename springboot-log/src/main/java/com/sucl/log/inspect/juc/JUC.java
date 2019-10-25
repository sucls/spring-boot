package com.sucl.log.inspect.juc;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * java.util.logging
 *  默认配置文件： [jre]/lib/logging.properties，通过设置系统属性java.util.logging.config.file修改
 *      或者通过修改VM options:-Djava.util.logging.config.file=F:\sucls\spring-boot\springboot-log\src\main\resources\logging.#.properties
 *      或者通过-Djava.util.logging.config.class=com.sucl.log.inspect.juc.LoggingPropertiesClassLoader
 *  日志高低级别：SEVERE（严重）、WARNING（警告）、INFO（信息）、CONFIG（配置）、FINE（详细）、FINER（较详细）、FINEST（非常详细）
 *
 * @author sucl
 * @since 2019/9/6
 */
public class JUC {

    private static final Logger log = Logger.getLogger(JUC.class.getName());

    public void init(){
        log.log(Level.INFO,"jul| JUL 日志");
    }

    public static void main(String[] args) {
        new JUC().init();
    }
}
