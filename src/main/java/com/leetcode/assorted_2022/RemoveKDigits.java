package com.leetcode.assorted_2022;

import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveKDigits {
    private static final Logger logger = LoggerFactory.getLogger(RemoveKDigits.class);

    public String removeKdigits(String num, int k) {
        var chars = num.toCharArray();

        var stack = new Stack<Character>();
        for (var i = 0; i < chars.length; i++) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > chars[i]) {
                stack.pop();
                //logger.info("[{}]", stack);
                k--;
            }
            stack.push(chars[i]);
            //logger.info("[{}]", stack);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        var builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        var result = builder.reverse().toString();
        logger.info("[{}]", result);
        while (result.startsWith("0")) {
            result = result.substring(1);
            logger.info("[{}]", result);
        }

        return result.length() == 0 ? "0" : result;
    }

    @Test
    public void test() {
        Assert.assertEquals(removeKdigits("1432219", 3), "1219");
        Assert.assertEquals(removeKdigits("100200", 1), "200");
        Assert.assertEquals(removeKdigits("10200", 1), "200");
    }
}
