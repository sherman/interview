package com.leetcode.assorted_2024;

public class MergeAlternately {
    public String mergeAlternately(String word1, String word2) {
        var w1 = 0;
        var w2 = 0;
        var result = new StringBuilder();

        while (w1 < word1.length() && w2 < word2.length()) {
            if (w1 < w2) {
                result.append(word1.charAt(w1));
                w1++;
            } else if (w1 > w2) {
                result.append(word2.charAt(w2));
                w2++;
            } else {
                result.append(word1.charAt(w1));
                result.append(word2.charAt(w2));
                w2++;
                w1++;
            }
        }

        while (w1 < word1.length()) {
            result.append(word1.charAt(w1));
            w1++;
        }

        while (w2 < word2.length()) {
            result.append(word2.charAt(w2));
            w2++;
        }

        return result.toString();
    }
}
