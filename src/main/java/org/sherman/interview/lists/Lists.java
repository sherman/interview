package org.sherman.interview.lists;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lists {
    private static final Logger log = LoggerFactory.getLogger(Lists.class);

    private Lists() {
    }

    public static Item reverse(@NotNull Item head) {
        Item current = head;
        Item prev = null;

        while (current != null) {
            Item next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            log.info("{} {}", prev.id, current != null ? current.id : null);
        }

        return prev;
    }

    public static int[] getIntersectionOfSortedLists(@NotNull int[] arr1, @NotNull int[] arr2) {
        Set<Integer> hash;

        if (arr1.length < arr2.length) {
            hash = Arrays.stream(arr1)
                    .boxed()
                    .collect(Collectors.toSet());

            return getIntersection(hash, arr2);
        } else {
            hash = Arrays.stream(arr2)
                    .boxed()
                    .collect(Collectors.toSet());

            return getIntersection(hash, arr1);
        }
    }

    public static Integer[] getIntersectionOfSortedListsConstMemory(@NotNull int[] arr1, @NotNull int[] arr2) {
        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                result.add(arr1[i]);
                i++;
                j++;
            }
        }

        return result.toArray(new Integer[result.size()]);
    }

    public static void print(@NotNull Item head) {
        Item tmp = head;
        while (tmp != null) {
            log.info("{}", tmp.id);
            tmp = tmp.next;
        }
    }

    private static int[] getIntersection(@NotNull Set<Integer> hash, @NotNull int[] arr) {
        return Arrays.stream(arr)
                .filter(hash::contains)
                .toArray();
    }
}
