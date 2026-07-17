package com.cognizant.tdd;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;
    private final List<String> auditLog = new ArrayList<>();

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public String registerUser(String email, String name) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists with email: " + email);
        }

        String userId = userRepository.save(email, name);
        notificationService.sendWelcomeEmail(email, name);
        auditLog.add("Registered user: " + email);

        return userId;
    }

    public boolean deactivateUser(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        String email = userRepository.findEmailById(userId);
        if (email == null) {
            return false;
        }

        boolean result = userRepository.deactivate(userId);
        if (result) {
            notificationService.sendDeactivationNotice(email);
            auditLog.add("Deactivated user: " + userId);
        }
        return result;
    }

    public List<String> getAuditLog() {
        return new ArrayList<>(auditLog);
    }
}
