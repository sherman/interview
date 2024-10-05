package com.leetcode.assorted_2024;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopKWordsV2 {
    public String[] getTopKWords(String[] words, int k) {
        // firstly create a freq. map
        var freqMap = new HashMap<String, Integer>();
        for (var word : words) {
            freqMap.putIfAbsent(word, 0);
            freqMap.compute(word, (_, value) -> value + 1);
        }

        var queue = new PriorityQueue<Map.Entry<String, Integer>>(Comparator.comparing(Map.Entry::getValue));
        for (var entry : freqMap.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else {
                var min = queue.peek();
                assert min != null;
                if (min.getValue() < entry.getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }

        var result = new ArrayList<String>(0);
        while (!queue.isEmpty()) {
            result.add(queue.poll().getKey());
        }
        Collections.reverse(result);
        return result.toArray(String[]::new);
    }

    @Test
    public void test() {
        Assert.assertEquals(
            getTopKWords(new String[] {"aa", "bb", "aa", "cc", "aa", "bb", "ff", "cc"}, 3),
            new String[] {"aa", "cc", "bb"}
        );
    }
}
