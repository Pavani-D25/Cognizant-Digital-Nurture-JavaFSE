package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {

    private static final Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        logger.info("=== SLF4J Logging Exercise: Error Messages and Warning Levels ===");
        logger.info("Demonstrating different log levels and their appropriate use cases\n");

        demonstrateLogLevels();

        logger.info("\n--- OrderService Demo ---");
        OrderService orderService = new OrderService();

        orderService.processOrder("ORD-1001", 250.00);
        orderService.processOrder("ORD-1002", 15000.00);
        orderService.processOrder("ORD-1003", -50.00);
        orderService.processOrder("", 100.00);

        logger.info("\n--- UserService Demo ---");
        UserService userService = new UserService();

        userService.findUser("user001");
        userService.findUser("user999");
        userService.findUser(null);
        userService.addUser("user003", "Charlie Brown");
        userService.addUser("user001", "Alice Modified");
        userService.removeUser("user002");
        userService.removeUser("user999");

        logger.info("\n=== Exercise Complete ===");
        logger.info("Check console output and logs/ directory for log files");
    }

    private static void demonstrateLogLevels() {
        logger.trace("TRACE level - finest granularity, typically disabled in production");
        logger.debug("DEBUG level - diagnostic info for developers during debugging");
        logger.info("INFO level - general operational messages about system flow");
        logger.warn("WARN level - potentially harmful situations that deserve attention");
        logger.error("ERROR level - error events that might still allow the system to continue");
    }
}
