package org.sherman.interview.misc;

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

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckAlienDict {
    private static final Logger log = LoggerFactory.getLogger(CheckAlienDict.class);

    public static boolean isOrdered(@NotNull List<String> words, @NotNull List<Character> ordering) {
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < ordering.size(); i++) {
            dict.put(ordering.get(i), i);
        }

        int maxLength = words.stream()
            .map(String::length)
            .max(Comparator.reverseOrder())
            .get();

        for (int i = 0; i <= maxLength; i++) {
            for (int j = 1; j < words.size(); j++) {
                int prevIndex = getCharIndex(words.get(j - 1), i, dict);
                int currentIndex = getCharIndex(words.get(j), i, dict);
                if (prevIndex > currentIndex) {
                    log.info("{} {}", words.get(j - 1), words.get(j));
                    return false;
                }
            }
        }

        return true;
    }

    private static int getCharIndex(String word, int index, Map<Character, Integer> dict) {
        char[] chars = word.toCharArray();
        if (index < chars.length) {
            return dict.get(chars[index]);
        } else {
            // by default
            return 0;
        }
    }
}
