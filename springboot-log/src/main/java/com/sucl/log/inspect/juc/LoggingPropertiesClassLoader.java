package com.sucl.log.inspect.juc;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * @author sucl
 * @since 2019/9/6
 */
public class LoggingPropertiesClassLoader {
    private static final String LOGGING_PROPERTIES_NAME = "logging.#.properties";

    public InputStream initLoggingProperties(){
        ClassLoader classLoader = LoggingPropertiesClassLoader.class.getClassLoader();

        return classLoader.getResourceAsStream(LOGGING_PROPERTIES_NAME);
    }

    public LoggingPropertiesClassLoader(){
        LogManager logManager = LogManager.getLogManager();
        InputStream in = initLoggingProperties();
        try {
            logManager.readConfiguration(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
