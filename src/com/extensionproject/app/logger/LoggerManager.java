package com.extensionproject.app.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LoggerManager {
    public static Logger getClassLog(Class<?> c) {
        return (Logger) LogManager.getLogger(c);
    }
}
