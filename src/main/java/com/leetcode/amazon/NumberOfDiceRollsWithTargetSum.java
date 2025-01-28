package com.leetcode.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfDiceRollsWithTargetSum {
    private static final Logger logger = LoggerFactory.getLogger(NumberOfDiceRollsWithTargetSum.class);

    public int numRollsToTarget(int n, int k, int target) {
        var total = 0;
        var sum = 0;
        for (var i = 1; i <= k; i++) {
            sum += i;
            total += recursion(n, k, target, sum);
            sum -= i;
        }
        return total;
    }

    private static int recursion(int n, int k, int target, int sum) {
        //logger.info("recursion n={} k={}", n, k);
        if (n == 1) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        var total = 0;
        for (var i = 1; i <= k; i++) {
            sum += i;
            total += recursion(n - 1, k, target, sum);
            sum -= i;
        }
        return total;
    }

    @Test
    public void test() {
        Assert.assertEquals(numRollsToTarget(2, 6, 3), 2);
        Assert.assertEquals(numRollsToTarget(1, 6, 3), 1);
        Assert.assertEquals(numRollsToTarget(2, 6, 7), 6);
        Assert.assertEquals(numRollsToTarget(3, 10, 20), 63);
    }
}
