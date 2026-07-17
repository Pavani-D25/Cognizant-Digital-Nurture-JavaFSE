package com.cognizant.tdd;

public interface UserRepository {
    String save(String email, String name);
    boolean existsByEmail(String email);
    String findEmailById(String userId);
    boolean deactivate(String userId);
}
