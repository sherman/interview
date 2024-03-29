package com.leetcode.assorted_2022;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DecodeWays {
    private static final Logger logger = LoggerFactory.getLogger(DecodeWays.class);

    private final Map<Call, Integer> cache = new HashMap<>();

    public int numDecodings(String s) {
        cache.clear();

        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        var chars = s.toCharArray();
        var x = treeStep(chars, 0, 1);
        var y = treeStep(chars, 0, 2);

        return x + y;
    }

    private int treeStep(char[] data, int i, int chars) {
        if (i + chars == data.length) {
            if (isValid(data, i, chars)) {
                return 1;
            } else {
                return 0;
            }
        }

        var total = 0;

        if (i < data.length) {
            if (i + 1 < data.length && isValid(data, i, chars)) {
                var call = new Call(i + chars, 1);
                var result = cache.get(call);
                if (result != null) {
                    total += result;
                } else {
                    var cnt = treeStep(data, i + chars, 1);
                    cache.put(call, cnt);
                    total += cnt;
                }
            }

            if (i + 2 < data.length && isValid(data, i, chars)) {
                var call = new Call(i + chars, 2);
                var result = cache.get(call);
                if (result != null) {
                    total += result;
                } else {
                    var cnt = treeStep(data, i + chars, 2);
                    cache.put(call, cnt);
                    total += cnt;
                }
            }
        }

        return total;
    }

    private boolean isValid(char[] data, int i, int chars) {
        var str = new StringBuilder();
        for (var k = i; k < chars + i; k++) {
            str.append(data[k]);
        }

        var numStr = str.toString();
        if (numStr.startsWith("0")) {
            return false;
        }

        int num = Integer.parseInt(numStr);

        logger.info("[{}] [{}]", numStr, num);

        return num >= 1 && num <= 26;
    }

    @Test
    public void test() {
        Assert.assertEquals(numDecodings("226"), 3);
        Assert.assertEquals(numDecodings("12"), 2);
        Assert.assertEquals(numDecodings("06"), 0);
        Assert.assertEquals(numDecodings("10"), 1);
        Assert.assertEquals(numDecodings("2101"), 1);
        Assert.assertEquals(numDecodings("111111111111111111111111111111111111111111111"), 1836311903);
    }

    private record Call(int index, int chars) {
    }
}
