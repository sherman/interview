package com.leetcode.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopFromKSortedArrays {
    private static final Logger logger = LoggerFactory.getLogger(TopFromKSortedArrays.class);

    public List<Integer> top(int[][] nums, int top) {
        var queue = new PriorityQueue<Entry>();

        // add first values from each array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].length > 0) {
                logger.info("Added element: " + nums[i][0]);
                queue.add(new Entry(nums[i][0], i, 0));
            }
        }

        var result = new ArrayList<Integer>();

        for (var i = 0; !queue.isEmpty() && result.size() < top; i++) {
            Entry curr = queue.poll();

            result.add(curr.value);

            var nextIndex = curr.index + 1;
            if (nextIndex < nums[curr.array].length) {
                var element = nums[curr.array][nextIndex];
                logger.info("Added element: " + element);
                queue.add(new Entry(element, curr.array, nextIndex));
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(List.of(3, 3, 3, 3, 3, 3), top(new int[][] {{3, 3, 3, 3, 3, 3, 3, 2, 1}, {2, 2, 1, 1}}, 6));
        Assert.assertEquals(List.of(7, 6, 5), top(new int[][] {{2, 1}, {3, 1}, {5, 4}, {7, 6}}, 3));
    }

    private record Entry(int value, int array, int index) implements Comparable<Entry> {
        @Override
        public int compareTo(@NotNull Entry o) {
            return Integer.compare(o.value, this.value);
        }
    }
}
