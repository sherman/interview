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

public class RemoveDuplicatesFromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        int current = -1;
        int lastIndex = 0;
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                current = nums[i];
                counter++;
                lastIndex++;
            } else {
                if (current == nums[i]) {
                    counter++;
                    if (counter < 3) {
                        if (lastIndex < i) {
                            nums[lastIndex] = current;
                        }
                        lastIndex++;
                    }
                } else {
                    current = nums[i];
                    counter = 1;
                    if (lastIndex < i) {
                        nums[lastIndex] = current;
                    }
                    lastIndex++;
                }
            }
        }

        System.out.println(lastIndex);
        return lastIndex;
    }
}
