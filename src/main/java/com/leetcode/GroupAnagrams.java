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

import java.util.*;

public class GroupAnagrams {
    /**
     * Given an array of strings, group anagrams together.
     * <p>
     * Example:
     * <p>
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note:
     * <p>
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     *
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> index = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String v = sort(strs[i]);
            List<String> words = index.get(v);
            if (words == null) {
                words = new ArrayList<>();
            }
            words.add(strs[i]);
            index.put(v, words);
        }

        List<List<String>> result = new ArrayList<>(index.values());
        return result;
    }

    private static String sort(String v) {
        char[] chars = v.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
