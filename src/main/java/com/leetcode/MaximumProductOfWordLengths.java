package com.leetcode;

/*
 * Copyright (C) 2018 by Denis M. Gabaydulin
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumProductOfWordLengths {
    private static final Logger log = LoggerFactory.getLogger(MaximumProductOfWordLengths.class);

    /**
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
     * Example 1:
     * Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
     * Return 16
     * The two words can be "abcw", "xtfn".
     * <p>
     * Example 2:
     * Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
     * Return 4
     * The two words can be "ab", "cd".
     * </p>
     * <p>
     * Example 3:
     * Given ["a", "aa", "aaa", "aaaa"]
     * Return 0
     * No such pair of words.
     * </p>
     */
    public static int maxProduct(String[] words) {
        int[][] arr = new int[26][];

        for (int i = 0; i < 26; i++) {
            arr[i] = new int[words.length];
        }

        for (int j = 0; j < words.length; j++) {
            char[] letters = words[j].toCharArray();

            for (char letter : letters) {
                arr[letter - 97][j] = arr[letter - 97][j] + 1;
            }
        }

        log.info("{}", arr);

        int maxSum = 0;

        for (int i = 0; i < words.length; i++) {
            int sum = 0;
            for (int k = 0; k < arr.length; k++) {
                sum += arr[k][i];
            }

            log.info("{}", sum);

            int maxSecond = 0;

            for (int j = 0; j < words.length; j++) {
                int secondSum = 0;
                if (i != j) {
                    for (int k = 0; k < arr.length; k++) {
                        secondSum += arr[k][j];
                        if (arr[k][j] > 0 && arr[k][i] > 0) {
                            secondSum = 0;
                            break;
                        }
                    }
                }

                log.info("s: {}", secondSum);

                maxSecond = Math.max(maxSecond, secondSum);
            }

            maxSum = Math.max(maxSum, sum * maxSecond);
        }

        return maxSum;
    }
}
