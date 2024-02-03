package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TopKWords {
    @SuppressWarnings("unchecked")
    public String[] getTopKWords(String[] words, int k) {
        if (k == 0 || words.length == 0) {
            return new String[]{
            };
        }

        if (k == 1 && words.length == 1) {
            return new String[]{words[0]};
        }

        var maxFreq = 0;
        var frequencies = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            int freq = frequencies.getOrDefault(words[i], 0);
            frequencies.put(words[i], freq + 1);
            maxFreq = Math.max(maxFreq, freq + 1);
        }

        var buckets = new Object[frequencies.size()];
        for (var entry : frequencies.entrySet()) {
            var value = buckets[entry.getValue()];
            if (value == null) {
                buckets[entry.getValue()] = new ArrayList<String>();
            }
            ((ArrayList<String>) buckets[entry.getValue()]).add(entry.getKey());
        }

        var result = new ArrayList<String>();
        for (var i = buckets.length - 1; k > 0; i--) {
            var collection = ((ArrayList<String>) buckets[i]);
            for (var element : collection) {
                result.add(element);
                k--;
                if (k == 0) {
                    break;
                }
            }
        }

        return result.toArray(String[]::new);
    }

    @Test
    public void test() {
        Assert.assertEquals(
            getTopKWords(new String[]{"aa", "bb", "aa", "cc", "aa", "bb", "ff", "cc"}, 3),
            new String[]{"aa", "bb", "cc"}
        );
    }

    private record WordFreq(String word, int freq) {
    }
}
