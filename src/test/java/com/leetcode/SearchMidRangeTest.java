package com.leetcode;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

/**
 * @author Denis M. Gabaydulin
 * @since 10.05.18
 */
public class SearchMidRangeTest {
    @Test
    public void searchRange() {
        ArrayAsserts.assertArrayEquals(new int[]{3, 4}, SearchMidRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        ArrayAsserts.assertArrayEquals(new int[]{-1, -1}, SearchMidRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        ArrayAsserts.assertArrayEquals(new int[]{0, 0}, SearchMidRange.searchRange(new int[]{1}, 1));
    }
}
