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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class ReorganizeString {
    private static final Logger log = LoggerFactory.getLogger(ReorganizeString.class);

    /***
     * sfffp -> fpfsf
     * aaab -> ""
     *
     * @param str
     * @return
     */

    public static String reorganizeString(String str) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : str.toCharArray()) {
            freq.putIfAbsent(c, 0);
            freq.put(c, freq.get(c) + 1);

            if (str.length() % 2 == 0) {
                if (freq.get(c) > str.length() / 2) {
                    return "";
                }
            } else {
                if (freq.get(c) > str.length() / 2 + 1) {
                    return "";
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        Character character = null;
        do {
            character = getNextChar(character, freq);
            if (character != null) {
                builder.append(character);
            }
        } while (character != null);

        log.info("{}", builder.toString());

        return builder.toString();
    }

    private static Character getNextChar(Character prev, Map<Character, Integer> freq) {
        Optional<Character> candidate = freq.keySet().stream()
            .filter(c -> c != prev)
            .filter(c -> freq.get(c) != 0)
            .sorted(Comparator.comparingInt((ToIntFunction<Character>) freq::get).reversed())
            .findFirst();

        if (candidate.isPresent()) {
            freq.put(candidate.get(), freq.get(candidate.get()) - 1);
            return candidate.get();
        } else {
            return null;
        }
    }
}
