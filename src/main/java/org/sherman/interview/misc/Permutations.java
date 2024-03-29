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

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    private static final Logger log = LoggerFactory.getLogger(Permutations.class);

    private Permutations() {
    }

    public static List<String> getPermutations(String str) {
        char[] chars = str.toCharArray();
        List<Character> characterList = new ArrayList<>();
        for (char chr : chars) {
            characterList.add(chr);
        }
        List<String> result = new ArrayList<>();
        collectPermutations(characterList, new ArrayList<>(), result, new AtomicInteger());
        return result;
    }

    private static void collectPermutations(List<Character> str, List<Character> selected, List<String> result, AtomicInteger count) {
        log.info("{} {} {}", count.get(), str, selected);
        if (str.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            selected.forEach(builder::append);
            log.info("{}", builder.toString());
            result.add(builder.toString());
        } else {
            for (int i = 0; i < str.size(); i++) {
                char c = str.remove(i);
                selected.add(c);

                count.incrementAndGet();
                collectPermutations(str, selected, result, count);

                str.add(i, c);
                selected.remove(selected.size() - 1);
            }
        }
    }
}
