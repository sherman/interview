package com.leetcode.assorted_2023;

import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DailyTemperature {
    private static final Logger logger = LoggerFactory.getLogger(DailyTemperature.class);

    public int[] dailyTemperatures(int[] temperatures) {
        var result = new int[temperatures.length];

        var stack = new Stack<Integer>();
        for (var i = temperatures.length - 1; i >= 0; i--) {
            logger.info("[{}]", stack);
            var current = temperatures[i];
            while (!stack.isEmpty() && current >= temperatures[stack.peek()]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            }

            stack.push(i);
        }

        logger.info("R: [{}]", result);

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(dailyTemperatures(
                new int[] {55, 38, 53, 81, 61, 93, 97, 32, 43, 78}),
            new int[] {3, 1, 1, 2, 1, 1, 0, 1, 1, 0}
        );
        Assert.assertEquals(dailyTemperatures(new int[] {73, 74, 75, 71, 69, 72, 76, 73}), new int[] {1, 1, 4, 2, 1, 1, 0, 0});
        Assert.assertEquals(dailyTemperatures(new int[] {30, 40, 50, 60}), new int[] {1, 1, 1, 0});
    }
}
