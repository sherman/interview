package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MaximumDistanceInArraysTest {
    @Test
    public void getMaxDistance() {
        assertEquals(MaximumDistanceInArrays.getMaxDistance(new int[][]{new int[]{1, 2, 3}, new int[]{4, 5}, new int[]{1, 2, 3}}), 4);
        assertEquals(MaximumDistanceInArrays.getMaxDistance(new int[][]{new int[]{1, 2, 3}, new int[]{1, 2, 3}, new int[]{1, 2, 3}}), 2);
        assertEquals(MaximumDistanceInArrays.getMaxDistance(new int[][]{new int[]{1, 2, 3, 4}, new int[]{1, 2}, new int[]{1, 2}}), 1);
    }
}
