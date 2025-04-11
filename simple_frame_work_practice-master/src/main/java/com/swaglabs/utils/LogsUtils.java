package com.swaglabs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {
    public static final  String logsPath ="test-outputs/Logs";
    private LogsUtils(){
        super();
    }

    public static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
        //return LogManager.getLogger(LogsUtils.class);
    }

    //... the three dots in the method signature allow us to pass several messages of arguments to the method.( array of strings )
    // EX: LogsUtils.info("This is a test log message 1", "This is a test log message 2", .......);
    public static void trace(String... message) {
        logger().trace(String.join(" ", message));
    }

    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }

    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }
}
