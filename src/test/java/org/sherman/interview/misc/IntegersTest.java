package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author sherman
 * @since 24.02.17
 */
public class IntegersTest {
    @Test
    public void isPalindrome() {
        assertFalse(Integers.isPalindrome(-2147483648));
        assertTrue(Integers.isPalindrome(0));
        assertFalse(Integers.isPalindrome(100));
        assertFalse(Integers.isPalindrome(10));
        assertTrue(Integers.isPalindrome(1001));
        assertTrue(Integers.isPalindrome(111));
        assertFalse(Integers.isPalindrome(1234));
        assertTrue(Integers.isPalindrome(221122));
        assertTrue(Integers.isPalindrome(391193));
    }
}
