package com.leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReverseBitsTest {
    @Test
    public void reverseBits() {
        assertEquals(ReverseBits.reverseBits(43261596), 964176192);
        assertEquals(ReverseBits.reverseBits(456), 327155712);
    }
}
