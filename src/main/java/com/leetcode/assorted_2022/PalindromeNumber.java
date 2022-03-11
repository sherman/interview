package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        int original = x;
        long rev = 0;
        while (x != 0) {
            rev = x % 10 + rev * 10;
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return false;
            }
            x = x / 10;
        }

        return original == rev;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPalindrome(121));
        Assert.assertFalse(isPalindrome(-121));
        Assert.assertFalse(isPalindrome(10));
        Assert.assertTrue(isPalindrome(1));
    }
}
