package com.leetcode.assorted_2022;

import com.google.common.collect.MinMaxPriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopKWords {
    public String[] getTopKWords(String[] words, int k) {
        if (k == 0 || words.length == 0) {
            return new String[] {
            };
        }

        if (k == 1 && words.length == 1) {
            return new String[] {words[0]};
        }

        var frequencies = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            int freq = frequencies.getOrDefault(words[i], 0);
            frequencies.put(words[i], freq + 1);
        }

        var queue = MinMaxPriorityQueue
            .orderedBy((Comparator<WordFreq>) (o1, o2) -> o2.freq() - o1.freq())
            .maximumSize(k)
            .create();

        for (var entry : frequencies.entrySet()) {
            queue.offer(new WordFreq(entry.getKey(), entry.getValue()));
        }

        var result = new String[k];
        int i = 0;
        while (!queue.isEmpty()) {
            result[i] = queue.poll().word();
            i++;
        }

        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(
            getTopKWords(new String[] {"aa", "bb", "aa", "cc", "aa", "bb", "ff", "cc"}, 3),
            new String[] {"aa", "cc", "bb"}
        );
    }

    private record WordFreq(String word, int freq) {
    }
}
