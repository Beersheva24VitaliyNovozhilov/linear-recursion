package io.p4r53c.telran.util.recursion;

/**
 * Contains methods that uses linear recursion.
 *
 * @author p4r53c
 */
public class RecursionMethods {

    private RecursionMethods() {
    }

    /**
     * Dummy demo CW method.
     *
     * @param n dummy parameter
     */
    public static void f(int n) {
        if (n > 3) {
            f(n - 1);
        }
    }

    /**
     * Calculates factorial of a number using recursion.
     *
     * @param n the number to calculate factorial
     * @return the factorial of the number
     */
    public static long factorial(int n) {
        if (n < 0) {
            n = -n;
        }

        return n == 0 ? 1 : n * factorial(n - 1);
    }

    /**
     * Calculates power of a number using recursion.
     *
     * @param x the base number
     * @param n the power to raise the base number to
     * @return the power of the number
     * 
     *         Helper: {@link #recursiveMultiply(int, int)}
     * 
     */
    public static int pow(int x, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        int result = 1;

        if (n > 0) {
            result = recursiveMultiply(x, pow(x, n - 1));
        }
        return result;
    }

    /**
     * Calculates square of a number using recursion.
     *
     * @param x the number to calculate square of
     * @return the square of the number
     */
    public static int square(int x) {
        int result = 0;

        if (x != 0) {
            result = x < 0 ? square(-x) : x + x - 1 + square(x - 1);
        }

        return result;
    }

    /**
     * Calculates sum of array elements using recursion.
     *
     * @param arr the array to calculate sum of
     * @return the sum of the array elements
     * 
     *         Helper: {@link #sum(int, int[])}
     * 
     */
    public static int sum(int[] arr) {
        return sum(0, arr);

    }

    /**
     * Checks if one string is substring of the other using recursion.
     *
     * @param string    the string to check
     * @param subString the substring to check for
     * @return true if the substring is found, false otherwise
     * 
     *         Helper: {@link #isPrefix(String, String)}
     */
    public static boolean isSubstring(String string, String subString) {
        boolean result = false;

        if (string.length() >= subString.length()) {
            result = isPrefix(string, subString) || isSubstring(string.substring(1), subString);
        }

        return result;

    }

    /**
     * Checks if one string is substring of the other using recursion without using
     * String class methods (almost :)).
     * 
     * Only conversion to char array, no {@link java.lang.String#charAt()},
     * {@link java.lang.String#length()} and {@link java.lang.String#substring()}
     * and other String class methods.
     * 
     * Without the {@link java.lang.String#toCharArray()} calls, I'm afraid I'd have
     * to go back to the methods that are avoided here. But I'm not sure.
     * 
     * Helper: {@link #isSubstringRecursive(char[], char[], int, int)}
     *
     * @param string    the string to check
     * @param subString the substring to check for
     * @return true if the substring is found, false otherwise
     */
    public static boolean isSubstringNoStandardMethods(String string, String subString) {
        char[] strArr = string.toCharArray();
        char[] subArr = subString.toCharArray();

        return isSubstringRecursive(strArr, subArr, 0, 0);
    }

    /**
     * Calculates fibonacci number using recursion.
     *
     * @param n the number to calculate fibonacci of
     * @return the fibonacci number
     */
    public static long fibonacci(int n) {
        return n < 2 ? n : fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Calculates sum of array elements using recursion.
     * 
     * Note: this method looks little bit different from CW because I wrote it
     * before end of explanation.
     * If this isn't acceptable, I'll refactor it.
     *
     * @param index the index to start from
     * @param arr   the array to calculate sum of
     * @return the sum of the array elements
     */
    private static int sum(int index, int[] arr) {
        return index < arr.length ? arr[index] + sum(index + 1, arr) : 0;
    }

    /**
     * Calculates multiplication of two numbers using recursion.
     *
     * @param x the number to multiply
     * @param n the number to multiply by
     * @return the multiplication result
     */
    private static int recursiveMultiply(int x, int n) {
        int result = 0;

        if (n < 0) {
            x = -x;
            n = -n;
        }

        if (n > 0) {
            result = x + recursiveMultiply(x, n - 1);
        }

        return result;
    }

    /**
     * Checks if one string is prefix of the other using recursion.
     *
     * @param string    the string to check
     * @param subString the substring to check for
     * @return true if the substring is found, false otherwise
     */
    private static boolean isPrefix(String string, String subString) {
        boolean result = false;

        if (subString.length() == 0) {
            result = true;
        } else if (string.charAt(0) == subString.charAt(0)) {
            result = isPrefix(string.substring(1), subString.substring(1));
        }

        return result;
    }

    /**
     * Checks if one string is substring of the other using recursion.
     *
     * @param strArr   the string to check
     * @param subArr   the substring to check for
     * @param strIndex the index in the string to start checking from
     * @param subIndex the index in the substring to start checking from
     * @return true if the substring is found, false otherwise
     * 
     *         Helpers: {@link #isSubStringFound(char[], int)},
     *         {@link #isEndOfString(char[], int)},
     *         {@link #charactersMatch(char[], char[], int, int)},
     * 
     */
    private static boolean isSubstringRecursive(char[] strArr, char[] subArr, int strIndex, int subIndex) {
        boolean result;

        if (isSubStringFound(subArr, subIndex)) {
            result = true;
        } else if (isEndOfString(strArr, strIndex)) {
            result = false;
        } else {
            if (charactersMatch(strArr, subArr, strIndex, subIndex)) {
                result = isSubstringRecursive(strArr, subArr, strIndex + 1, subIndex + 1);
            } else {
                result = isSubstringRecursive(strArr, subArr, strIndex + 1, 0);
            }
        }

        return result;
    }

    /**
     * Checks if the given index is at the end of the substring.
     *
     * @param subArr   the substring to check
     * @param subIndex the index in the substring to check
     * @return true if the index is at the end of the substring, false otherwise
     */
    private static boolean isSubStringFound(char[] subArr, int subIndex) {
        return subIndex == subArr.length;
    }

    /**
     * Checks if the given index is at the end of the string.
     *
     * @param strArr   the string to check
     * @param strIndex the index in the string to check
     * @return true if the index is at the end of the string, false otherwise
     */
    private static boolean isEndOfString(char[] strArr, int strIndex) {
        return strIndex == strArr.length;
    }

    /**
     * Checks if the characters at the given indices in the two arrays match.
     *
     * @param strArr   the first array
     * @param subArr   the second array
     * @param strIndex the index in the first array
     * @param subIndex the index in the second array
     * @return true if the characters match, false otherwise
     */
    private static boolean charactersMatch(char[] strArr, char[] subArr, int strIndex, int subIndex) {
        return strArr[strIndex] == subArr[subIndex];
    }

}
