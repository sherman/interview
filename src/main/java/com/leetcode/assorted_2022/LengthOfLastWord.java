package com.leetcode.assorted_2022;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LengthOfLastWord {
    public int lengthOfLastWord(String data) {
        if (data.isEmpty()) {
            return 0;
        }

        int state = 0; // 0 - not started, 1 - word started

        var length = 0;
        var chars = data.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                // word is stopped
                if (state == 1) {
                    return length;
                }
            } else {
                // a word is here
                state = 1;
                length++;
            }
        }

        return length;
    }

    @Test
    public void lengthOfLastWord() {
        Assert.assertEquals(lengthOfLastWord(""), 0);
        Assert.assertEquals(lengthOfLastWord("data"), 4);
        Assert.assertEquals(lengthOfLastWord("data a"), 1);
        Assert.assertEquals(lengthOfLastWord("data fefef       fefa"), 4);
        Assert.assertEquals(lengthOfLastWord("  data fefef       fefa  "), 4);
    }
}
