package com.cognizant.tdd;

public interface NotificationService {
    void sendWelcomeEmail(String email, String name);
    void sendDeactivationNotice(String email);
}
