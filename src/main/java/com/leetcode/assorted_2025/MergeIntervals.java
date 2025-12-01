package com.leetcode.assorted_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.<int[]>comparingInt(i -> i[0])
            .thenComparingInt(i -> i[1]));

        var result = new ArrayList<int[]>();
        for (var interval : intervals) {
            if (result.isEmpty()) {
                result.add(interval);
            } else {
                var last = result.getLast();
                if (interval[0] <= last[1]) {
                    last = result.getLast();
                    if (interval[0] <= last[1]) {
                        last[0] = Math.min(interval[0], last[0]);
                        last[1] = Math.max(interval[1], last[1]);
                    } else {
                        result.add(interval);
                    }
                } else {
                    result.add(interval);
                }
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    @Test
    public void test1() {
        var data = new int[][] {new int[] {1, 3}, new int[] {2, 6}, new int[] {8, 10}, new int[] {15, 18}};
        ArrayAsserts.assertArrayEquals(merge(data), new int[][] {new int[] {1, 6}, new int[] {8, 10}, new int[] {15, 18}});
    }

    @Test
    public void test2() {
        var data = new int[][] {new int[] {1, 3}};
        ArrayAsserts.assertArrayEquals(merge(data), new int[][] {new int[] {1, 3}});
    }
}
