package com.leetcode.assorted_2022;

import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompressWord {
    private static final Logger logger = LoggerFactory.getLogger(CompressWord.class);

    public String compressWord(String word, int k) {
        if (k == 1) {
            return "";
        }

        Character current = null;
        var chars = word.toCharArray();
        int count = 0;

        var skips = new HashSet<Integer>();

        while (true) {
            for (int i = 0; i < chars.length; i++) {
                // init char
                if (current == null) {
                    current = chars[i];
                    count = 1;
                } else {
                    // increment count
                    if (current == chars[i]) {
                        count++;
                    } else {
                        // char is changed
                        if (count < k) {
                            current = chars[i];
                            count = 1;
                        }
                    }
                }

                if (k == count) {
                    skips.add(i - k + 1);
                    count = 0;
                    current = 0;
                }
            }

            if (skips.isEmpty()) {
                break;
            }

            //logger.info("[{}]", skips);
            var newStr = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (!skips.contains(i)) {
                    newStr.append(chars[i]);
                } else {
                    i = i + k - 1;
                    skips.remove(i);
                }
            }

            //logger.info("[{}]", newStr.toString());
            chars = newStr.toString().toCharArray();

            skips.clear();
        }

        return new String(chars);
    }

    @Test
    public void test() {
        Assert.assertEquals(compressWord("a", 1), "");
        Assert.assertEquals(compressWord("abbcccb", 3), "a");
        Assert.assertEquals(compressWord("aaabbbccc", 3), "");
        Assert.assertEquals(compressWord("abaaabbccc", 3), "a");
    }
}
