package com.solv.inventory.config;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class Log4j2Config {
    public static Logger logger = null;

    public Log4j2Config(String className) {
        logger = LogManager.getLogger(className);
    }

    public static Logger getLoggerVariable(String className) {
        new Log4j2Config(className.getClass().getName());
        return logger;
    }
}
