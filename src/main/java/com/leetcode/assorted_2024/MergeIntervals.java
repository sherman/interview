package com.leetcode.assorted_2024;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        var sorted = new TreeSet<Interval>(
            Comparator
                .comparing(interval -> ((Interval) interval).left)
                .thenComparing(interval -> ((Interval) interval).right)
        );

        for (var i = 0; i < intervals.length; i++) {
            var interval = intervals[i];
            var current = new Interval(interval[0], interval[1]);
            sorted.add(current);
        }

        var result = new ArrayList<int[]>();
        Interval current = null;
        for (var interval : sorted) {
            if (current == null) {
                current = interval;
            } else {
                if (interval.left >= current.left && interval.left <= current.right) {
                    current.left = Math.min(interval.left, current.left);
                    current.right = Math.max(interval.right, current.right);
                } else if (interval.right >= current.left && interval.left <= current.right) {
                    current.left = interval.left;
                    current.right = Math.max(interval.right, current.right);
                } else {
                    result.add(new int[]{current.left, current.right});
                    current = interval;
                }
            }
        }

        if (current != null) {
            result.add(new int[]{current.left, current.right});
        }

        var primitive = new int[result.size()][2];
        for (var i = 0; i < result.size(); i++) {
            primitive[i] = result.get(i);
        }
        return primitive;
    }

    private static class Interval {
        private int left;
        private int right;

        Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return left == interval.left && right == interval.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
    }

    @Test
    public void test1() {
        var data = new int[][]{new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}};
        ArrayAsserts.assertArrayEquals(merge(data), new int[][]{new int[]{1, 6}, new int[]{8, 10}, new int[]{15, 18}});
    }

    @Test
    public void test2() {
        var data = new int[][]{new int[]{1, 3}};
        ArrayAsserts.assertArrayEquals(merge(data), new int[][]{new int[]{1, 3}});
    }
}
