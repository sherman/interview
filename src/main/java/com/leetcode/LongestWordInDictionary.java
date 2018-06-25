package com.leetcode;

import org.sherman.interview.trie.Trie;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class LongestWordInDictionary {
    public static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // TODO: replace with tree map
        Map<Integer, List<String>> groupByLength = Arrays.stream(words)
            .collect(Collectors.groupingBy(String::length, Collectors.toList()));

        List<Integer> orderedKeys = groupByLength.keySet().stream()
            .sorted(Comparator.comparingInt((ToIntFunction<Integer>) value -> value).reversed())
            .collect(Collectors.toList());

        String candidate = "";
        for (Integer length : orderedKeys) {
            SortedSet<String> candidates = new TreeSet<>();
            for (String word : groupByLength.get(length)) {
                boolean failed = false;
                for (int i = 0; i < word.length(); i++) {
                    if (!trie.search(word.substring(0, word.length() - i))) {
                        failed = true;
                        break;
                    }
                }

                if (!failed) {
                    candidates.add(word);
                }
            }

            if (!candidates.isEmpty()) {
                return candidates.first();
            }
        }

        return candidate;
    }
}
