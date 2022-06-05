package org.sherman.interview.vector;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArrayUtilsTest {
    @Test
    public void hasIntersectionVector() {
        Assert.assertFalse(ArrayUtils.hasIntersectionVector(
            new int[] {1, 100, 101, 201, 201, 202, 2000, 3000},
            new int[] {2, 102, 103, 203, 204, 205, 206, 207})
        );

        Assert.assertFalse(ArrayUtils.hasIntersectionVector(
            new int[] {1, 100, 101, 201, 201, 202, 2000, 3000, 3001, 3002, 3003, 3004, 3005, 3006, 3007, 3008},
            new int[] {2, 102, 103, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215})
        );

        Assert.assertFalse(ArrayUtils.hasIntersectionVector(
            new int[] {1, 2, 3, 4, 5, 6, 7, 8},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );

        Assert.assertTrue(ArrayUtils.hasIntersectionVector(
            new int[] {1, 2, 3, 4, 5, 6, 7, 9},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );

        Assert.assertTrue(ArrayUtils.hasIntersectionVector(
            new int[] {1, 2, 3, 4, 5, 6, 7, 16},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );
    }

    @Test
    public void hasIntersectionScalar() {
        Assert.assertFalse(ArrayUtils.hasIntersectionScalar(
            new int[] {1, 100, 101, 201, 201, 202, 2000, 3000},
            new int[] {2, 102, 103, 203, 204, 205, 206, 207})
        );

        Assert.assertFalse(ArrayUtils.hasIntersectionScalar(
            new int[] {1, 2, 3, 4, 5, 6, 7, 8},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );

        Assert.assertTrue(ArrayUtils.hasIntersectionScalar(
            new int[] {1, 2, 3, 4, 5, 6, 7, 9},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );

        Assert.assertTrue(ArrayUtils.hasIntersectionScalar(
            new int[] {1, 2, 3, 4, 5, 6, 7, 16},
            new int[] {9, 10, 11, 12, 13, 14, 15, 16})
        );
    }

    @Test
    public void sumVector() {
        Assert.assertEquals(ArrayUtils.sumVector(new int[] {1, 2, 3, 4, 1, 2, 3, 4}), 20);
        Assert.assertEquals(ArrayUtils.sumVector(new int[] {1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4}), 40);
    }
}
