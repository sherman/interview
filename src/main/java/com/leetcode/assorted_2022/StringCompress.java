package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringCompress {
    public int compress(char[] chars) {
        // in case of a single character return original uncompressed string
        if (chars.length == 1) {
            return 1;
        }

        var length = 0;
        var current = chars[0];
        var groupCounter = 1;
        for (var i = 1; i < chars.length; i++) {
            // if current character is the same, increment a group counter
            if (current == chars[i]) {
                groupCounter++;
            } else {
                // compress group
                chars[length++] = current;
                if (groupCounter > 1) {
                    var digits = String.valueOf(groupCounter).toCharArray();
                    for (var c : digits) {
                        chars[length++] = c;
                    }
                }
                // update next group
                current = chars[i];
                groupCounter = 1;
            }
        }

        // handle last group
        if (groupCounter > 0) {
            // compress group
            chars[length++] = current;
            if (groupCounter > 1) {
                var digits = String.valueOf(groupCounter).toCharArray();
                for (var c : digits) {
                    chars[length++] = c;
                }
            }
        }

        return length;
    }

    @Test
    public void test() {
        var data1 = new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        Assert.assertEquals(compress(data1), 4);
        Assert.assertEquals(new String(data1), "ab12bbbbbbbbb");
    }
}
