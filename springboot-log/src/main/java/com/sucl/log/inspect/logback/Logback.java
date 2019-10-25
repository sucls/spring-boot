package com.sucl.log.inspect.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sucl
 * @since 2019/9/6
 */
public class Logback {

    private static final Logger log = LoggerFactory.getLogger(Logback.class);

    public void init(){
        log.info("logback| logback信息");
    }
}
