package com.leetcode.assorted_2022;

import java.util.LinkedList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        List<int[]> merge = new LinkedList<>();
        int i = 0;

        // not overlapping
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            merge.add(intervals[i]);
            i++;
        }

        // add overlapping
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        merge.add(newInterval);

        // add rest
        while (i < intervals.length) {
            merge.add(intervals[i]);
            i++;
        }

        return merge.toArray(new int[merge.size()][]);
    }

    @Test
    public void test() {
        Assert.assertEquals(new int[][] {{1, 5}, {6, 8}}, insert(new int[][] {{1, 5}}, new int[] {6, 8}));
        Assert.assertEquals(new int[][] {{1, 7}}, insert(new int[][] {{1, 5}}, new int[] {2, 7}));
        Assert.assertEquals(new int[][] {{5, 7}}, insert(new int[][] {}, new int[] {5, 7}));
        Assert.assertEquals(new int[][] {{1, 5}, {6, 9}}, insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}));
        Assert.assertEquals(new int[][] {{1, 9}}, insert(new int[][] {{1, 9}}, new int[] {2, 3}));
        Assert.assertEquals(
            new int[][] {{1, 2}, {3, 10}, {12, 16}},
            insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})
        );
    }
}
