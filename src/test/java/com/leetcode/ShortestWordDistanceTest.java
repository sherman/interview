package com.leetcode;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 06.03.18
 */
public class ShortestWordDistanceTest {
    @Test
    public void getShortestWordDistance() {
        assertEquals(ShortestWordDistance.getShortestWordDistance(new String[]{"aa", "cc", "bb", "aa", "ff", "bb", "mm", "aa"}, "aa", "bb"), 1);
        assertEquals(ShortestWordDistance.getShortestWordDistance(new String[]{"aa", "cc", "bb", "aa", "ff", "cc", "mm", "cc", "ff", "aa"}, "aa", "cc"), 1);
    }
}
