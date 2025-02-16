package com.leetcode.uber;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class IntersectionOfIntervals {
    private static final Logger logger = LoggerFactory.getLogger(IntersectionOfIntervals.class);

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        var i = 0;
        var j = 0;
        var result = new ArrayList<int[]>();
        while (i < firstList.length && j < secondList.length) {
            var first = firstList[i];
            var second = secondList[j];
            var start = Math.max(first[0], second[0]);
            var end = Math.min(first[1], second[1]);
            if (start <= end) {
                result.add(new int[] {start, end});
            }

            if (first[1] < second[1]) {
                i++;
            } else {
                j++;
            }
        }
        return result.toArray(int[][]::new);
    }

    @Test
    public void test() {
        intervalIntersection(new int[][] {{14, 16}}, new int[][] {{7, 13}, {16, 20}});
        intervalIntersection(new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}});
    }
}
