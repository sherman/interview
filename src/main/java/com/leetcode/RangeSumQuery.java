package com.leetcode;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RangeSumQuery {
    private static final Logger log = LoggerFactory.getLogger(RangeSumQuery.class);

    private final int[] nums;
    private final Map<Pair<Integer, Integer>, Integer> cache = new HashMap();

    public RangeSumQuery(int[] nums) {
        this.nums = nums;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                log.info("{} {}", i, j);
                int sum = getSum(i, j);
                log.info("{}", sum);
            }
        }
    }

    private int getSum(int from, int to) {
        Pair<Integer, Integer> key = new Pair<>(from, to);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (to == from) {
            cache.put(key, nums[to]);
            return nums[to];
        }

        if (to - from == 1) {
            cache.put(key, nums[from] + nums[to]);
            return nums[from] + nums[to];
        }

        int sum = getSum(from, to - 1) + getSum(to, to);
        cache.put(key, sum);
        return sum;
    }

    public int sumRange(int from, int to) {
        return cache.get(new Pair<>(from, to));
    }
}
