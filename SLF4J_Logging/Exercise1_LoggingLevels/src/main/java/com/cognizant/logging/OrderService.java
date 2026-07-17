package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void processOrder(String orderId, double amount) {
        logger.debug("Processing order {} with amount ${}", orderId, amount);

        if (orderId == null || orderId.isBlank()) {
            logger.error("Order ID cannot be null or empty. Received: '{}'", orderId);
            throw new IllegalArgumentException("Invalid order ID");
        }

        if (amount <= 0) {
            logger.warn("Order {} has non-positive amount: ${}. Setting default amount.", orderId, amount);
            amount = 9.99;
        }

        if (amount > 10000) {
            logger.warn("High-value order detected: {} with amount ${}", orderId, amount);
        }

        try {
            boolean success = validatePayment(orderId, amount);
            if (success) {
                logger.info("Order {} processed successfully. Amount: ${}", orderId, amount);
            } else {
                logger.warn("Payment failed for order {}. Retrying...", orderId);
                retryPayment(orderId, amount);
            }
        } catch (Exception e) {
            logger.error("Unexpected error while processing order {}: {}", orderId, e.getMessage(), e);
        }

        logger.debug("Finished processing order {}", orderId);
    }

    private boolean validatePayment(String orderId, double amount) {
        logger.trace("Validating payment for order {}, amount ${}", orderId, amount);
        return amount < 5000;
    }

    private void retryPayment(String orderId, double amount) {
        logger.info("Retrying payment for order {}", orderId);
        logger.debug("Retry attempt for order {} with amount ${}", orderId, amount);
    }
}
