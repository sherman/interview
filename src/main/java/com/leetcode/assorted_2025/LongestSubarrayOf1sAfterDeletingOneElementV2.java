package com.leetcode.assorted_2025;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubarrayOf1sAfterDeletingOneElementV2 {
    public int longestSubarray(int[] nums) {
        var queue = new LinkedList<Integer>();
        var maxLength = 0;
        for (var i = 0; i < nums.length; i++) {
            var current = nums[i];
            if (current == 0) {
                if (queue.isEmpty()) {
                    queue.add(current);
                } else {
                    // check if top is zero
                    if (isTopZero(queue)) {
                        queue.poll();
                        maxLength = Math.max(queue.size(), maxLength);
                    } else {
                        if (queue.contains(0)) {
                            maxLength = Math.max(queue.size() - 1, maxLength);
                            dropUntilZeroInclusive(queue);
                        }
                    }
                    queue.add(current);
                }
            } else {
                queue.add(current);
            }
            var x = 1;
        }
        if (isTopZero(queue)) {
            queue.poll();
        }
        if (queue.contains(0)) {
            maxLength = Math.max(queue.size() - 1, maxLength);
        } else {
            maxLength = Math.max(maxLength, queue.size() == nums.length ? queue.size() - 1 : queue.size());
        }
        return maxLength;
    }

    private static boolean isTopZero(Queue<Integer> queue) {
        var top = queue.peek();
        return top != null && top == 0;
    }

    private static void dropUntilZeroInclusive(Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            var current = queue.poll();
            if (current == 0) {
                break;
            }
        }
    }

    @Test
    public void test() {
        Assertions.assertEquals(4, longestSubarray(new int[] {1, 1, 0, 0, 1, 1, 1, 0, 1}));
        Assertions.assertEquals(2, longestSubarray(new int[] {0, 0, 1, 1}));
        Assertions.assertEquals(0, longestSubarray(new int[] {0, 0, 0}));
        Assertions.assertEquals(2, longestSubarray(new int[] {1, 1, 1}));
        Assertions.assertEquals(5, longestSubarray(new int[] {0, 1, 1, 1, 0, 1, 1, 0, 1}));
        Assertions.assertEquals(3, longestSubarray(new int[] {1, 1, 0, 1}));
    }
}
