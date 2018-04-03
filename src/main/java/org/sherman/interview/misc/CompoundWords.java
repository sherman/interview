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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompoundWords {
    /**
     * @return list words which are compound of two words: w1 + w2, where w1 and w2 are in the dict, but a compound word is not
     */
    public static List<String> getWords(@NotNull Set<String> dict, @NotNull List<String> data) {
        return data.stream()
            .filter(word -> isCompound(dict, word))
            .collect(Collectors.toList());
    }

    private static boolean isCompound(@NotNull Set<String> dict, @NotNull String word) {
        if (dict.contains(word)) {
            return false;
        }

        for (String dictWord : dict) {
            if (word.startsWith(dictWord)) {
                String suffix = word.substring(dictWord.length());
                if (dict.contains(suffix)) {
                    return true;
                }
            }
        }

        return false;
    }
}
