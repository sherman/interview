package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 10/10/2016
 */
public class MaxSumTest {
    @Test
    public void maxSum() {
        assertEquals(MaxSum.getMaxSum(new int[]{1, 2, 3}), new Integer[]{1, 2, 3});
        assertEquals(MaxSum.getMaxSum(new int[]{0, -1}), new Integer[]{0});
        assertEquals(MaxSum.getMaxSum(new int[]{1, 1, 1, -1, 2, 1, 2}), new Integer[]{2, 1, 2});
        assertEquals(MaxSum.getMaxSum(new int[]{100, 0, -1, 99}), new Integer[]{100, 0});
        assertEquals(MaxSum.getMaxSum(new int[]{-1, 0, 3, 4, 0, -1}), new Integer[]{0, 3, 4, 0});
        assertEquals(MaxSum.getMaxSum(new int[]{100, 200, -1000, 1, 2}), new Integer[]{100, 200});
    }
}
