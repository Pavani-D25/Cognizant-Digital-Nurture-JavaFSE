package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final Map<String, String> userDatabase = new HashMap<>();

    public UserService() {
        userDatabase.put("user001", "Alice Johnson");
        userDatabase.put("user002", "Bob Smith");
        logger.info("UserService initialized with {} users", userDatabase.size());
    }

    public String findUser(String userId) {
        logger.debug("Looking up user with ID: {}", userId);

        if (userId == null) {
            logger.error("Attempted to look up user with null ID");
            return null;
        }

        String name = userDatabase.get(userId);
        if (name == null) {
            logger.warn("User not found for ID: {}. Total users in DB: {}", userId, userDatabase.size());
        } else {
            logger.info("Found user: {} -> {}", userId, name);
        }
        return name;
    }

    public void addUser(String userId, String name) {
        logger.debug("Adding new user: {} -> {}", userId, name);

        if (userId == null || name == null) {
            logger.error("Cannot add user with null userId or name. userId={}, name={}", userId, name);
            throw new IllegalArgumentException("UserId and name must not be null");
        }

        if (userDatabase.containsKey(userId)) {
            logger.warn("User {} already exists. Overwriting with new name: {}", userId, name);
        }

        userDatabase.put(userId, name);
        logger.info("User added successfully. Total users now: {}", userDatabase.size());
    }

    public void removeUser(String userId) {
        logger.debug("Removing user: {}", userId);

        if (!userDatabase.containsKey(userId)) {
            logger.warn("Cannot remove user {}: not found in database", userId);
            return;
        }

        String removed = userDatabase.remove(userId);
        logger.info("Removed user: {} -> {}. Remaining users: {}", userId, removed, userDatabase.size());
    }
}
