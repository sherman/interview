package com.leetcode.assorted_2023;

import java.util.Stack;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SplitAStringInBalancedStrings {
    public int balancedStringSplit(String s) {
        if (s.length() == 0) {
            return 0;
        }

        var stack = new Stack<Character>();
        Character push = null;
        Character pop = null;
        if (s.charAt(0) == 'R') {
            push = 'R';
        } else {
            push = 'L';
        }

        var count = 0;
        stack.push(s.charAt(0));
        for (var i = 1; i < s.length(); i++) {
            if (stack.isEmpty()) {
                if (s.charAt(i) == 'R') {
                    push = 'R';
                } else {
                    push = 'L';
                }
            }

            if (s.charAt(i) == push) {
                stack.push(push);
            } else {
                stack.pop();
            }

            if (stack.isEmpty()) {
                count++;
            }
        }

        return count;
    }

    @Test
    public void test() {
        Assert.assertEquals(balancedStringSplit("RLRRLLRLRL"), 4);
        Assert.assertEquals(balancedStringSplit("RLRRRLLRLL"), 2);
        Assert.assertEquals(balancedStringSplit("LLLLRRRR"), 1);
        Assert.assertEquals(balancedStringSplit("RLLLLRRRLR"), 3);
    }
}
