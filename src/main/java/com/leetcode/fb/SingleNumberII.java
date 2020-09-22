package com.leetcode.fb;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> data = new HashMap<>();

        for (int num : nums) {
            int counter = data.computeIfAbsent(num, ignored -> 0);
            data.put(num, counter + 1);
        }

        for (int num : data.keySet()) {
            if (data.get(num) == 1) {
                return num;
            }
        }

        throw new IllegalArgumentException("Unreachable!");
    }

    @Test
    public void test() {
        Assert.assertEquals(singleNumber(new int[]{2, 2, 2, 1}), 1);
        Assert.assertEquals(singleNumber(new int[]{1, 1, 5, 3, 3, 3, 1}), 5);
    }
}
