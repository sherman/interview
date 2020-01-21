package com.leetcode;

/*
 * Copyright (C) 2019 by Denis M. Gabaydulin
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

public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> notInIndex = new ArrayList<>();

        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            index.put(arr2[i], 0);
        }

        for (int i = 0; i < arr1.length; i++) {
            Integer counter = index.get(arr1[i]);
            if (counter == null) {
                notInIndex.add(arr1[i]);
            } else {
                counter++;
                index.put(arr1[i], counter);
            }
        }

        int j = 0;
        for (int i = 0; i < arr2.length; i++) {
            int counter = index.get(arr2[i]);
            for (int k = 0; k < counter; k++) {
                arr1[j] = arr2[i];
                j++;
            }
        }

        Collections.sort(notInIndex);

        for (int v : notInIndex) {
            arr1[j] = v;
            j++;
        }

        return arr1;
    }
}
