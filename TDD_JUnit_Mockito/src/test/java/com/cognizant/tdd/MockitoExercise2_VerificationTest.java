package com.cognizant.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Mockito Exercise 2: Verifying Interactions")
class MockitoExercise2_VerificationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NotificationService notificationService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, notificationService);
    }

    @Test
    @DisplayName("Verify method was called exactly once")
    void testVerifyOnce() {
        when(userRepository.existsByEmail("alice@test.com")).thenReturn(false);
        when(userRepository.save("alice@test.com", "Alice")).thenReturn("u1");

        userService.registerUser("alice@test.com", "Alice");

        verify(userRepository, times(1)).save("alice@test.com", "Alice");
        verify(notificationService, times(1)).sendWelcomeEmail("alice@test.com", "Alice");
    }

    @Test
    @DisplayName("Verify method was never called")
    void testVerifyNever() {
        when(userRepository.existsByEmail("nobody@test.com")).thenReturn(true);

        assertThrows(RuntimeException.class,
            () -> userService.registerUser("nobody@test.com", "Ghost"));

        verify(notificationService, never()).sendWelcomeEmail(anyString(), anyString());
    }

    @Test
    @DisplayName("Verify at least and at most calls")
    void testVerifyAtLeastAtMost() {
        when(userRepository.existsByEmail("multi@test.com")).thenReturn(false);
        when(userRepository.save("multi@test.com", "Multi")).thenReturn("u2");

        userService.registerUser("multi@test.com", "Multi");

        verify(userRepository, atLeastOnce()).existsByEmail("multi@test.com");
        verify(userRepository, atMost(3)).existsByEmail("multi@test.com");
    }

    @Test
    @DisplayName("Verify deactivation triggers notification")
    void testVerifyDeactivation() {
        when(userRepository.findEmailById("u100")).thenReturn("deactivate@test.com");
        when(userRepository.deactivate("u100")).thenReturn(true);

        boolean result = userService.deactivateUser("u100");

        assertTrue(result);
        verify(notificationService).sendDeactivationNotice("deactivate@test.com");
        verify(userRepository).deactivate("u100");
    }

    @Test
    @DisplayName("Verify no interactions at all")
    void testVerifyNoInteractions() {
        assertThrows(IllegalArgumentException.class,
            () -> userService.registerUser(null, null));

        verifyNoInteractions(userRepository);
        verifyNoInteractions(notificationService);
    }

    @Test
    @DisplayName("Verify interactions with timeout")
    void testVerifyWithTimeout() {
        when(userRepository.existsByEmail("slow@test.com")).thenReturn(false);
        when(userRepository.save("slow@test.com", "Slow")).thenReturn("u3");

        userService.registerUser("slow@test.com", "Slow");

        verify(userRepository, timeout(1000).times(1)).save("slow@test.com", "Slow");
    }

    @Test
    @DisplayName("Verify order of interactions")
    void testVerifyOrder() {
        when(userRepository.existsByEmail("order@test.com")).thenReturn(false);
        when(userRepository.save("order@test.com", "Order")).thenReturn("u4");

        userService.registerUser("order@test.com", "Order");

        var inOrder = inOrder(userRepository, notificationService);
        inOrder.verify(userRepository).existsByEmail("order@test.com");
        inOrder.verify(userRepository).save("order@test.com", "Order");
        inOrder.verify(notificationService).sendWelcomeEmail("order@test.com", "Order");
    }

    @Test
    @DisplayName("Verify audit log is maintained")
    void testVerifyAuditLog() {
        when(userRepository.existsByEmail("audit@test.com")).thenReturn(false);
        when(userRepository.save("audit@test.com", "Audit")).thenReturn("u5");

        userService.registerUser("audit@test.com", "Audit");

        assertEquals(1, userService.getAuditLog().size());
        assertTrue(userService.getAuditLog().get(0).contains("audit@test.com"));
    }
}
