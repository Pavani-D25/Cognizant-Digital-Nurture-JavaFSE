package com.cognizant.tdd;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public boolean isValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public String normalize(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase();
    }
}
