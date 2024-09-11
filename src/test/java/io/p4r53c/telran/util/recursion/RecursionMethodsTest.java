package io.p4r53c.telran.util.recursion;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Test;

import static io.p4r53c.telran.util.recursion.RecursionMethods.*;

class RecursionMethodsTest {

    // For linter: Some meaningful assertion instead of empty method call
    void testF() {
        assertDoesNotThrow(() -> f(400));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 120",
            "-3, 6"
    })
    void testFactorial(int input, int expected) {
        assertEquals(expected, factorial(input));
    }

    @ParameterizedTest
    @CsvSource({
            "100, 10, 2",
            "100, -10, 2",
            "1000, 10, 3",
            "-1000, -10, 3"
    })
    void testPow(int expected, int base, int input) {
        assertEquals(expected, pow(base, input));
    }

    @ParameterizedTest
    @CsvSource({
            "10, -3"
    })
    void testPowThrowsException(int base, int input) {
        assertThrowsExactly(IllegalArgumentException.class, () -> pow(base, input));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 25",
            "-5, 25",
            "0, 0",
            "100, 10000"
    })
    void testSquare(int input, int expected) {
        int actual = square(input);
        assertEquals(expected, actual);
    }

    @Test
    void testSum() {
        int[] arr = { 1, 2, 3, 4, 5 };
        assertEquals(15, sum(arr));
    }

    @Test
    void testIsSubstring() {
        assertTrue(isSubstring("hello", ""));
        assertFalse(isSubstring("", "hello"));
        assertTrue(isSubstring("hello world", "hello"));
        assertTrue(isSubstring("hello world", "world"));
        assertTrue(isSubstring("hello world", "ld"));
    }

    /**
     * Checks if one byte stream is substring of the other using recursion.
     * 
     * Only here we use {@link java.lang.String#getBytes} and
     * {@link java.io.ByteArrayInputStream}
     *
     * @throws IOException if there is a problem reading from the streams
     */
    @Test
    void testIsSubstringByteStreams() throws IOException {
        String string = "hello world";
        String subString = "orl";

        InputStream stringBytes = new ByteArrayInputStream(string.getBytes());
        InputStream subStringBytes = new ByteArrayInputStream(subString.getBytes());

        assertTrue(isSubstringByteStreams(stringBytes, subStringBytes));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 1",
            "2, 1",
            "3, 2",
            "4, 3",
            "10, 55"
    })
    void testFibonacci(int input, int expected) {
        assertEquals(expected, fibonacci(input));
    }

}
