package com.leetcode.assorted_2024;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportSpamMessage {
    public boolean reportSpam(String[] message, String[] bannedWords) {
        var hashed = buildFreqMap(bannedWords);

        var counter = 0;
        for (var word : message) {
            if (hashed.containsKey(word)) {
                var current = hashed.get(word);
                if (current == 1) {
                    hashed.remove(word);
                }
                counter++;
            }

            if (counter == 2) {
                return true;
            }
        }
        return false;
    }

    private Map<String, Integer> buildFreqMap(String[] words) {
        var hashed = new HashMap<String, Integer>();
        for (var word : words) {
            hashed.putIfAbsent(word, 0);
            hashed.compute(word, (k, charFreq) -> charFreq - 1);
        }
        return hashed;
    }

    @Test
    public void test() {
        Assert.assertTrue(reportSpam(new String[] {"l", "i", "l", "i", "l"}, new String[] {"d", "a", "i", "v", "a"}));
        /*Assert.assertTrue(reportSpam(new String[] {"hello", "world", "leetcode"}, new String[] {"world", "hello"}));
        Assert.assertFalse(reportSpam(new String[] {"hello", "world", "leetcode"}, new String[] {"a", "hello"}));
        Assert.assertFalse(reportSpam(new String[] {}, new String[] {"a", "hello"}));*/
    }
}
