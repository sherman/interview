package org.sherman.interview.sort.counting;

/*
 * Copyright (C) 2015 by Denis M. Gabaydulin
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CountingSort {
    private static final Logger log = LoggerFactory.getLogger(CountingSort.class);


    public static int[] sort(@NotNull int[] elts, int max) {
        Map<Integer, Integer> histogram = new HashMap<>();

        for (int elt : elts) {
            Integer counter = histogram.putIfAbsent(elt, 1);
            if (counter != null) {
                histogram.put(elt, ++counter);
            }
        }

        histogram.forEach((k, v) -> log.info("h: {}: {}", k, v));

        int[] countArray = IntStream.range(0, max)
                .map(index -> Optional.ofNullable(histogram.get(index)).orElse(0))
                .toArray();

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] = countArray[i - 1] + countArray[i];
        }

        log.info("c: {}", countArray);

        int[] result = new int[elts.length];

        for (int elt : elts) {
            result[countArray[elt] - 1] = elt;
            --countArray[elt];
        }

        Arrays.stream(result).forEach(elt -> log.info("r: {}", elt));

        return result;
    }
}
