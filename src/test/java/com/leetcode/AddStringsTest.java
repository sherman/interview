package com.leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddStringsTest {
    @Test
    public void addStrings() {
        assertEquals(AddStrings.addStrings("0", "0"), "0");
        assertEquals(AddStrings.addStrings("9", "99"), "108");
    }
}
