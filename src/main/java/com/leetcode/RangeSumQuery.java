package com.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.util.Pair;

public class RangeSumQuery {
    private static final Logger log = LoggerFactory.getLogger(RangeSumQuery.class);

    private final int[] nums;
    private final Map<Pair<Integer, Integer>, Integer> cache = new HashMap();

    public RangeSumQuery(int[] nums) {
        this.nums = nums;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                cache.put(new Pair<>(i, j), sum);
            }
        }
    }

    public int sumRange(int from, int to) {
        return cache.get(new Pair<>(from, to));
    }
}
