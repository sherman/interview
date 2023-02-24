package io.algoexpert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Function;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class MergeIntervals {

    public int[][] mergeOverlappingIntervals(int[][] intervals) {
        var sorted = new TreeSet<>(
            (Comparator<Interval>) (o1, o2) -> Comparator.comparing((Function<Interval, Integer>) interval -> interval.one)
                .thenComparing(interval -> interval.two)
                .compare(o1, o2)
        );

        for (var i = 0; i < intervals.length; i++) {
            sorted.add(new Interval(intervals[i][0], intervals[i][1]));
        }

        // 1,1 and 1.2 and 2,3
        var result = new ArrayList<int[]>();

        var current = new int[2];
        var empty = true;
        for (var element : sorted) {
            if (empty) {
                current[0] = element.one;
                current[1] = element.two;
                empty = false;
            } else {
                if (isOverlapped(element.one, current)) {
                    current[0] = Math.min(element.one, current[0]);
                    current[1] = Math.max(element.two, current[1]);
                } else {
                    result.add(current);
                    current = new int[2];
                    current[0] = element.one;
                    current[1] = element.two;
                }
            }
        }

        result.add(current);

        return result.toArray(int[][]::new);
    }

    private static boolean isOverlapped(int number, int[] interval) {
        if (interval[0] <= number && number <= interval[1]) {
            return true;
        } else {
            return false;
        }
    }

    private static class Interval {
        private final int one;
        private final int two;

        Interval(int one, int two) {
            this.one = one;
            this.two = two;
        }
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(
            mergeOverlappingIntervals(new int[][] {{1, 2}, {3, 5}, {4, 7}, {6, 8}, {9, 10}}),
            new int[][] {{1, 2}, {3, 8}, {9, 10}}
        );
    }
}
