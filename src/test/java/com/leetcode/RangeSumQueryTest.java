package com.leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RangeSumQueryTest {
    @Test
    public void sumRange() {
        RangeSumQuery rangeSumQuery = new RangeSumQuery(
            new int[] {-2, 0, 3, -5, 2, -1}
        );

        assertEquals(rangeSumQuery.sumRange(0, 2), 1);
        assertEquals(rangeSumQuery.sumRange(2, 5), -1);
        assertEquals(rangeSumQuery.sumRange(0, 5), -3);

        rangeSumQuery = new RangeSumQuery(
            new int[] {-1}
        );

        assertEquals(rangeSumQuery.sumRange(0, 0), -1);

        rangeSumQuery = new RangeSumQuery(
            new int[] {-4, -5}
        );

        assertEquals(rangeSumQuery.sumRange(0, 0), -4);
    }
}
