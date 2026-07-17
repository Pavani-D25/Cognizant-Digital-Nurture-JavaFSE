package com.cognizant.tdd;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Exercise 4: AAA Pattern, Test Fixtures, Setup and Teardown")
class Exercise4_AAAandFixturesTest {

    private Calculator calculator;
    private EmailValidator emailValidator;
    private List<String> testResults;

    @BeforeAll
    static void initTestSuite() {
        System.out.println("=== Starting JUnit Test Suite ===");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        emailValidator = new EmailValidator();
        testResults = new ArrayList<>();
        System.out.println("  [Setup] Fresh instances created for each test");
    }

    @AfterEach
    void tearDown() {
        testResults.clear();
        calculator = null;
        emailValidator = null;
        System.out.println("  [Teardown] Resources cleaned up");
    }

    @AfterAll
    static void completeTestSuite() {
        System.out.println("=== Test Suite Complete ===");
    }

    @Test
    @DisplayName("AAA Pattern: Addition with Arrange-Act-Assert")
    void testAdditionAAA() {
        // Arrange
        int a = 10;
        int b = 20;
        int expected = 30;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(expected, result, "Addition should produce correct sum");
    }

    @Test
    @DisplayName("AAA Pattern: Factorial with Arrange-Act-Assert")
    void testFactorialAAA() {
        // Arrange
        int input = 5;
        int expected = 120;

        // Act
        int result = calculator.factorial(input);

        // Assert
        assertEquals(expected, result, "5! should equal 120");
    }

    @Test
    @DisplayName("AAA Pattern: Email validation with Arrange-Act-Assert")
    void testEmailValidationAAA() {
        // Arrange
        String validEmail = "user@example.com";
        String invalidEmail = "not-an-email";

        // Act
        boolean isValid = emailValidator.isValid(validEmail);
        boolean isInvalid = emailValidator.isValid(invalidEmail);

        // Assert
        assertTrue(isValid, "Valid email should pass validation");
        assertFalse(isInvalid, "Invalid email should fail validation");
    }

    @Test
    @DisplayName("Test Fixture: Using shared setUp objects")
    void testWithFixture() {
        // Uses the calculator and emailValidator created in @BeforeEach
        assertNotNull(calculator, "Calculator should be initialized by @BeforeEach");
        assertNotNull(emailValidator, "EmailValidator should be initialized by @BeforeEach");

        assertEquals(100, calculator.multiply(10, 10));
    }

    @Test
    @DisplayName("Test tracking: collecting results across test steps")
    void testResultTracking() {
        // Arrange
        testResults.add("step1");

        // Act
        int result = calculator.add(1, 1);
        testResults.add("step2");

        // Assert
        assertEquals(2, result);
        testResults.add("step3");
        assertEquals(3, testResults.size(), "All three steps should be tracked");
    }

    @Test
    @DisplayName("Multiple AAA blocks in one test for different scenarios")
    void testMultipleScenarios() {
        // Scenario 1: Normal division
        int result1 = calculator.divide(10, 2);
        assertEquals(5, result1);

        // Scenario 2: Division with remainder
        int result2 = calculator.divide(7, 2);
        assertEquals(3, result2, "Integer division truncates");

        // Scenario 3: Division of zero
        int result3 = calculator.divide(0, 5);
        assertEquals(0, result3, "Zero divided by number is zero");
    }
}
