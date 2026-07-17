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
@DisplayName("Mockito Exercise 1: Mocking and Stubbing")
class MockitoExercise1_StubbingTest {

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
    @DisplayName("Stub a method to return a specific value")
    void testStubbingReturnValue() {
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);
        when(userRepository.save("alice@example.com", "Alice")).thenReturn("user-001");

        String userId = userService.registerUser("alice@example.com", "Alice");

        assertEquals("user-001", userId);
    }

    @Test
    @DisplayName("Stub multiple return values (sequential calls)")
    void testStubbingSequential() {
        when(userRepository.existsByEmail("bob@example.com"))
            .thenReturn(false)
            .thenReturn(true);

        userService.registerUser("bob@example.com", "Bob");

        boolean exists = userRepository.existsByEmail("bob@example.com");
        assertTrue(exists, "Second call should return true");
    }

    @Test
    @DisplayName("Stub void method to perform side effect")
    void testStubbingVoid() {
        when(userRepository.existsByEmail("charlie@example.com")).thenReturn(false);
        when(userRepository.save("charlie@example.com", "Charlie")).thenReturn("user-003");

        doNothing().when(notificationService).sendWelcomeEmail("charlie@example.com", "Charlie");

        String userId = userService.registerUser("charlie@example.com", "Charlie");
        assertEquals("user-003", userId);
        verify(notificationService).sendWelcomeEmail("charlie@example.com", "Charlie");
    }

    @Test
    @DisplayName("Stub method to throw exception")
    void testStubbingException() {
        when(userRepository.existsByEmail("duplicate@example.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> userService.registerUser("duplicate@example.com", "Duplicate"));

        assertEquals("User already exists with email: duplicate@example.com", exception.getMessage());
    }

    @Test
    @DisplayName("Stub with argument matchers")
    void testArgumentMatchers() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(anyString(), anyString())).thenReturn("generated-id");

        String id1 = userService.registerUser("any@email.com", "Any User");
        String id2 = userService.registerUser("another@email.com", "Another User");

        assertEquals("generated-id", id1);
        assertEquals("generated-id", id2);
        verify(userRepository, times(2)).save(anyString(), anyString());
    }

    @Test
    @DisplayName("Stub method call count verification")
    void testStubbingCallCount() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userRepository.save("test@example.com", "Test")).thenReturn("user-010");

        userService.registerUser("test@example.com", "Test");

        verify(userRepository, times(1)).existsByEmail("test@example.com");
        verify(userRepository, times(1)).save("test@example.com", "Test");
        verify(notificationService, times(1)).sendWelcomeEmail("test@example.com", "Test");
    }

    @Test
    @DisplayName("Verify no more interactions after stubbed call")
    void testNoMoreInteractions() {
        when(userRepository.existsByEmail("done@example.com")).thenReturn(false);
        when(userRepository.save("done@example.com", "Done")).thenReturn("user-done");

        userService.registerUser("done@example.com", "Done");

        verifyNoMoreInteractions(userRepository);
        verify(notificationService).sendWelcomeEmail("done@example.com", "Done");
    }
}
