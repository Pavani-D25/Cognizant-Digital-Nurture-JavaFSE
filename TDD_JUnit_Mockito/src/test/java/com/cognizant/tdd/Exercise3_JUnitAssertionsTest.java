package com.cognizant.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Exercise3_JUnitAssertionsTest {

    private final Calculator calc = new Calculator();

    @Test
    @DisplayName("assertEquals - verify two values are equal")
    void testAssertEquals() {
        assertEquals(10, calc.add(4, 6), "4 + 6 should be 10");
        assertEquals(0, calc.subtract(5, 5), "5 - 5 should be 0");
    }

    @Test
    @DisplayName("assertNotEquals - verify two values differ")
    void testAssertNotEquals() {
        assertNotEquals(5, calc.add(2, 2), "2 + 2 should not be 5");
        assertNotEquals(-1, calc.multiply(3, 3), "3 * 3 should not be -1");
    }

    @Test
    @DisplayName("assertTrue and assertFalse - boolean conditions")
    void testBooleanAssertions() {
        assertTrue(calc.isEven(4), "4 should be even");
        assertTrue(calc.isEven(0), "0 should be even");
        assertFalse(calc.isEven(7), "7 should not be even");
        assertFalse(calc.isEven(-3), "-3 should not be even");
    }

    @Test
    @DisplayName("assertNull and assertNotNull - null checks")
    void testNullAssertions() {
        EmailValidator validator = new EmailValidator();
        assertNotNull(validator, "Validator should not be null");
        assertNull(validator.normalize(null), "Normalizing null should return null");
        assertNotNull(validator.normalize("test@email.com"), "Normalized email should not be null");
    }

    @Test
    @DisplayName("assertThrows - exception verification")
    void testExceptionAssertions() {
        assertThrows(ArithmeticException.class, 
            () -> calc.divide(1, 0));
        
        assertThrows(IllegalArgumentException.class, 
            () -> calc.factorial(-5),
            "Factorial of negative number should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("assertDoesNotThrow - verify no exception occurs")
    void testNoException() {
        assertDoesNotThrow(() -> calc.add(1, 2));
        assertDoesNotThrow(() -> calc.factorial(5));
    }

    @Test
    @DisplayName("assertAll - grouped assertions with lambda")
    void testGroupedAssertions() {
        assertAll("Calculator operations",
            () -> assertEquals(5, calc.add(2, 3)),
            () -> assertEquals(1, calc.subtract(4, 3)),
            () -> assertEquals(12, calc.multiply(3, 4)),
            () -> assertEquals(2, calc.divide(10, 5))
        );
    }

    @Test
    @DisplayName("assertIterableEquals - compare collections")
    void testIterableAssertion() {
        List<Integer> expected = Arrays.asList(2, 4, 6, 8);
        List<Integer> actual = Arrays.asList(2, 4, 6, 8);
        assertIterableEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Parameterized test - even number check")
    @ValueSource(ints = {0, 2, 4, 6, 100, -2})
    void testEvenNumbers(int number) {
        assertTrue(calc.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @DisplayName("Parameterized test - addition with CSV data")
    @CsvSource({
        "1, 1, 2",
        "0, 0, 0",
        "-1, 1, 0",
        "100, 200, 300",
        "-5, -3, -8"
    })
    void testParameterizedAddition(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }
}
