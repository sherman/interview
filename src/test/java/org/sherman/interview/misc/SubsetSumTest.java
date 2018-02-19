package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

/**
 * @author Denis Gabaydulin
 * @since 18.10.16
 */
public class SubsetSumTest {
    @Test
    public void getIndexOfSum() {
        assertArrayEquals(SubsetSum.getIndexOfSum(new int[]{5, 8, 7, 2}, 12), new int[]{0, 2});
        assertArrayEquals(SubsetSum.getIndexOfSum(new int[]{1, 1, 1, 1}, 12), new int[]{});
        assertArrayEquals(SubsetSum.getIndexOfSum(new int[]{1, 11, 1, 1}, 12), new int[]{0, 1});
        assertArrayEquals(SubsetSum.getIndexOfSum(new int[]{1, 2, 3, 4, 8}, 12), new int[]{3, 4});
    }

    @Test
    public void getIndexOfSumInSortedArray() {
        assertArrayEquals(SubsetSum.getIndexOfSumInSortedArray(new int[]{2, 5, 8, 7}, 12), new int[]{1, 3});
        assertArrayEquals(SubsetSum.getIndexOfSumInSortedArray(new int[]{1, 1, 1, 1}, 12), new int[]{});
        assertArrayEquals(SubsetSum.getIndexOfSumInSortedArray(new int[]{1, 1, 1, 11}, 12), new int[]{0, 3});
        assertArrayEquals(SubsetSum.getIndexOfSumInSortedArray(new int[]{1, 2, 3, 4, 8}, 12), new int[]{3, 4});
    }

    @Test
    public void getShortestSubsetOfSum() {
        assertArrayEquals(SubsetSum.getShortestSubsetOfSum(new int[]{2, 4, 3, 1, 7, 5}, 7), new int[]{4});
        assertArrayEquals(SubsetSum.getShortestSubsetOfSum(new int[]{2, 4, 4, 1, 5, 5}, 7), new int[]{});
        assertArrayEquals(SubsetSum.getShortestSubsetOfSum(new int[]{1, 1, 1, 2, 4, 4, 4, 4}, 3), new int[]{2, 3});
        assertArrayEquals(SubsetSum.getShortestSubsetOfSum(new int[]{1, 1, 1, 1, 4, 4, 4, 4}, 3), new int[]{0, 1, 2});
        assertArrayEquals(SubsetSum.getShortestSubsetOfSum(new int[]{1, 2, 3, 4, 5, 4, 5, 1}, 10), new int[]{5, 6, 7});
    }
}
