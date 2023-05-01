package com.leetcode.assorted_2023;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        var result = new StringBuilder();

        var length = Integer.MAX_VALUE;
        for (var str : strs) {
            length = Math.min(str.length(), length);
        }

        for (var i = 0; i < length; i++) {
            Character ch = null;
            var count = 0;
            for (var k = 0; k < strs.length; k++) {
                if (ch == null) {
                    ch = strs[k].charAt(i);
                } else {
                    if (ch != strs[k].charAt(i)) {
                        break;
                    }
                }
                count++;
            }
            if (count == strs.length) {
                result.append(ch);
            } else {
                return result.toString();
            }
        }

        return result.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(longestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");
        Assert.assertEquals(longestCommonPrefix(new String[] {"a"}), "a");
    }
}
