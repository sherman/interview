package com.leetcode;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis M. Gabaydulin
 * @since 06.03.18
 */
public class ShortestWordDistance {
    private static final Logger log = LoggerFactory.getLogger(ShortestWordDistance.class);

    /**
     * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
     * For example,
     * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
     * Given word1 = “coding”, word2 = “practice”, return 3.
     * Given word1 = "makes", word2 = "coding", return 1.
     */

    public static int getShortestWordDistance(@NotNull String[] words, @NotNull String word1, @NotNull String word2) {
        int current = 0;
        int currentDistance = Integer.MAX_VALUE;
        int lastIndex = -1;

        for (int i = 0; i < words.length; i++) {
            // no word before
            if (current == 0) {
                if (words[i].equals(word1)) {
                    current = 1;
                }

                if (words[i].equals(word2)) {
                    current = 2;
                }

                lastIndex = i;
            } else if (current == 1) {
                if (words[i].equals(word1)) {
                    lastIndex = i;
                }

                if (words[i].equals(word2)) {
                    current = 2;
                    currentDistance = Math.min(currentDistance, i - lastIndex);
                    lastIndex = i;
                }
            } else {
                if (words[i].equals(word1)) {
                    current = 1;
                    currentDistance = Math.min(currentDistance, i - lastIndex);
                    lastIndex = i;
                }

                if (words[i].equals(word2)) {
                    lastIndex = i;
                }
            }

            log.info("{} {}", words[i], currentDistance);
        }

        return currentDistance;
    }
}
