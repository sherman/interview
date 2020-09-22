package com.leetcode;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi) {
                result.add(new int[]{lo, hi});
            }

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(
                intervalIntersection(
                        new int[][]{new int[]{0, 2}, new int[]{5, 10}, new int[]{13, 23}, new int[]{24, 25}},
                        new int[][]{new int[]{1, 5}, new int[]{8, 12}, new int[]{15, 24}, new int[]{25, 26}}
                ),
                new int[][]{new int[]{1, 2}, new int[]{5, 5}, new int[]{8, 10}, new int[]{15, 23}, new int[]{24, 24}, new int[]{25, 25}}
        );

        ArrayAsserts.assertArrayEquals(
                intervalIntersection(
                        new int[][]{new int[]{0, 2}, new int[]{5, 10}},
                        new int[][]{new int[]{10, 12}}
                ),
                new int[][]{new int[]{10, 10}}
        );

        ArrayAsserts.assertArrayEquals(
                intervalIntersection(
                        new int[][]{new int[]{0, 2}, new int[]{5, 10}},
                        new int[][]{new int[]{11, 12}}
                ),
                new int[][]{}
        );

        ArrayAsserts.assertArrayEquals(
                intervalIntersection(
                        new int[][]{},
                        new int[][]{}
                ),
                new int[][]{}
        );

        ArrayAsserts.assertArrayEquals(
                intervalIntersection(
                        new int[][]{new int[]{3, 10}},
                        new int[][]{new int[]{5, 10}}
                ),
                new int[][]{new int[]{5, 10}}
        );
    }
}
