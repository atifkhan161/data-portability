package org.dataportabilityproject.gateway;

import java.lang.Thread.UncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** Starts the reference api server. */
public class ApiMain {
    private static final Logger logger = LoggerFactory.getLogger(ApiMain.class);

    public static void main(String args) {
        logger.warn("Starting reference api server.");
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread thread, Throwable t) {
                logger.warn("Uncaught exception in thread: {}", thread.getName(), t);
            }
        });
        throw new UnsupportedOperationException("Implement me!");
    }
}
