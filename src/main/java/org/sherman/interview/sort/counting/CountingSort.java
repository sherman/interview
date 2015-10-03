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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountingSort {
    private static final Logger log = LoggerFactory.getLogger(CountingSort.class);


    public static long[] sort(@NotNull long[] elts, long min, long max) {
        Map<Long, Integer> histogram = new HashMap<>();

        for (long elt : elts) {
            Integer counter = histogram.putIfAbsent(elt, 1);
            if (counter != null) {
                histogram.put(elt, ++counter);
            }
        }

        histogram
                .forEach((k, v) -> log.info("{}: {}", k, v));

        int total = 0;
        for (long elt : histogram.keySet()) {
            int old = histogram.get(elt);
            histogram.put(elt, total);
            total += old;
        }

        histogram
                .forEach((k, v) -> log.info("{}: {}", k, v));

        long[] result = new long[elts.length];
        for (long elt : elts) {
            result[histogram.get(elt)] = elt;
            histogram.put(elt, histogram.get(elt) + 1);
        }

        Arrays.stream(result).forEach(elt -> log.info("{}", elt));

        return result;
    }
}
