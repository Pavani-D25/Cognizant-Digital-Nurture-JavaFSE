package com.cognizant.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Exercise1_SettingUpJUnitTest {

    @Test
    @DisplayName("Verify JUnit 5 is working correctly")
    void testJUnitIsWorking() {
        Calculator calc = new Calculator();
        assertNotNull(calc, "Calculator instance should not be null");
    }

    @Test
    @DisplayName("Basic addition should return correct sum")
    void testAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(5, 3);
        assertEquals(8, result, "5 + 3 should equal 8");
    }

    @Test
    @DisplayName("Basic subtraction should return correct difference")
    void testSubtraction() {
        Calculator calc = new Calculator();
        int result = calc.subtract(10, 4);
        assertEquals(6, result, "10 - 4 should equal 6");
    }

    @Test
    @DisplayName("Multiplication should return correct product")
    void testMultiplication() {
        Calculator calc = new Calculator();
        int result = calc.multiply(6, 7);
        assertEquals(42, result, "6 * 7 should equal 42");
    }

    @Test
    @DisplayName("Division should return correct quotient")
    void testDivision() {
        Calculator calc = new Calculator();
        int result = calc.divide(20, 5);
        assertEquals(4, result, "20 / 5 should equal 4");
    }

    @Test
    @DisplayName("Division by zero should throw exception")
    void testDivisionByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, 
            () -> calc.divide(10, 0),
            "Dividing by zero should throw ArithmeticException");
    }
}
