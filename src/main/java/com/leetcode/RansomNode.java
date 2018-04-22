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

import java.util.HashMap;
import java.util.Map;

public class RansomNode {
    /**
     * Given an arbitrary ransom note string and another string containing letters from all the magazines,
     * write a function that will return true if the ransom note can be constructed from the magazines; otherwise, it will return false.
     * Each letter in the magazine string can only be used once in your ransom note.
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> freq = new HashMap<>();

        char[] chars = magazine.toCharArray();

        for (char aChar : chars) {
            Integer counter = freq.get(aChar);
            if (counter == null) {
                counter = 0;
            }
            counter++;
            freq.put(aChar, counter);
        }

        chars = ransomNote.toCharArray();

        for (char aChar : chars) {
            Integer counter = freq.get(aChar);
            if (counter == null) {
                return false;
            }

            if (counter == 0) {
                return false;
            }

            counter--;
            freq.put(aChar, counter);
        }

        return true;
    }
}
