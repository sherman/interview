package com.leetcode.amazon;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfDiceRollsWithTargetSum {
    private static final Logger logger = LoggerFactory.getLogger(NumberOfDiceRollsWithTargetSum.class);

    private final Map<Key, Integer> cache = new HashMap<>();

    public int numRollsToTarget(int n, int k, int target) {
        cache.clear();
        var total = 0;
        var sum = 0;
        for (var i = 1; i <= k; i++) {
            total += recursion(n, k, i, target, sum + i);
        }
        return total;
    }

    private int recursion(int n, int k, int item, int target, int sum) {
        //logger.info("recursion n={} k={}", n, k);
        if (n == 1) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        var cacheKey = new Key(item, sum);
        var result = cache.get(cacheKey);
        if (result != null) {
            return result;
        }

        var total = 0;
        for (var i = 1; i <= k; i++) {
            total += recursion(n - 1, k, i, target, sum + i);
        }

        cache.put(cacheKey, total);

        return total;
    }

    private record Key(int dice, int sum) {
    }

    @Test
    public void test() {
        Assert.assertEquals(numRollsToTarget(10, 20, 60), 3712);
        Assert.assertEquals(numRollsToTarget(4, 100, 100), 9506);
        Assert.assertEquals(numRollsToTarget(2, 6, 7), 6);
        Assert.assertEquals(numRollsToTarget(2, 6, 3), 2);
        Assert.assertEquals(numRollsToTarget(1, 6, 3), 1);
        Assert.assertEquals(numRollsToTarget(3, 10, 20), 63);
    }
}
