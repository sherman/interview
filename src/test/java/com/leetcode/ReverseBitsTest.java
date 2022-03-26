package com.leetcode;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class ReverseBitsTest {
    @Test
    public void reverseBits() {
        assertEquals(ReverseBits.reverseBits(43261596), 964176192);
        assertEquals(ReverseBits.reverseBits(456), 327155712);
    }
}
