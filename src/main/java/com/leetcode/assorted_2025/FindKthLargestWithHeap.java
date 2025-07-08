package com.leetcode.assorted_2025;

import java.util.PriorityQueue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindKthLargestWithHeap {
    public int findKthLargest(int[] nums, int k) {
        var result = new PriorityQueue<Integer>();

        for (var item : nums) {
            result.add(item);
            if (result.size() > k) {
                result.poll();
            }
        }

        assert !result.isEmpty();
        return result.poll();
    }

    @Test
    public void test() {
        Assert.assertEquals(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2), 5);
        Assert.assertEquals(findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
    }
}
