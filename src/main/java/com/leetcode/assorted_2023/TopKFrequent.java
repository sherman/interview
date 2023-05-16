package com.leetcode.assorted_2023;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // firstly count a frequency
        var freq = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            var count = freq.getOrDefault(nums[i], 0);
            freq.put(nums[i], count + 1);
        }

        // make a heap with leaner time
        var queue = new PriorityQueue<>(freq.entrySet().stream().map(Entry::new).toList());

        // find top k
        var result = new int[k];
        for (var i = 0; i < result.length; i++) {
            var entry = queue.poll();
            if (entry != null) {
                result[i] = entry.key;
            }
        }

        return result;
    }

    private static class Entry implements Comparable<Entry> {
        private final int key;
        private final int value;


        private Entry(Map.Entry<Integer, Integer> entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        @Override
        public int compareTo(@NotNull TopKFrequent.Entry o) {
            return o.value - this.value;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2), new int[] {1, 2});
    }
}
