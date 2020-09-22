package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[][]{};
        }
        int max = Math.max(A[A.length - 1][1], B[B.length - 1][1]);

        int p1 = 0;
        int p2 = 0;
        int prev1 = 0;
        int prev2 = 0;
        int state = 0; // 0 - no, 1 - inside
        List<int[]> res = new ArrayList<>();
        int[] current = null;
        int[] i1 = null;
        int[] i2 = null;
        for (int i = 0; i < max; i++) {
            int[] interval1 = getInterval(p1, A);
            int[] interval2 = getInterval(p2, B);

            if (!Arrays.equals(i1, interval1) || !Arrays.equals(i2, interval2)) {
                if (state == 1) {
                    current[1] = prev1;
                    state = 0;
                    res.add(current);
                    current = null;
                }
            }

            i1 = interval1;
            i2 = interval2;

            if (interval2 != null && interval1 != null && inInterval(p1, interval1) && inInterval(p2, interval2)) {
                if (state == 0) {
                    current = new int[2];
                    current[0] = p1;
                    state = 1;
                }
            } else {
                if (state == 1) {
                    current[1] = prev1;
                    state = 0;
                    res.add(current);
                    current = null;
                }
            }

            prev1 = p1;
            prev2 = p2;
            if (p1 > p2) {
                p2++;
            } else if (p1 < p2) {
                p1++;
            } else {
                p1++;
                p2++;
            }
        }

        if (state == 1) {
            current[1] = Math.min(i1[1], i2[1]);
            state = 0;
            res.add(current);
            current = null;
        }

        int[][] r = new int[res.size()][];
        for (int i = 0; i < r.length; i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    private boolean inInterval(int pointer, int[] data) {
        return pointer >= data[0] && pointer <= data[1];
    }

    private int[] getInterval(int pointer, int[][] intervals) {
        for (int[] interval : intervals) {
            if (inInterval(pointer, interval)) {
                return interval;
            }
        }
        return null;
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
