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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public static List<String> findMissingRanges(@NotNull int[] data, int start, int end) {
        List<String> result = new ArrayList<>();

        if (data[0] > start) {
            result.add(getRange(start, data[0] - 1));
        }

        for (int i = 1; i < data.length; i++) {
            if (data[i] - data[i - 1] > 1) {
                result.add(getRange(data[i - 1] + 1, data[i] - 1));
            }
        }

        if (data[data.length - 1] < end) {
            result.add(getRange(data[data.length - 1] + 1, end));
        }

        return result;
    }

    private static String getRange(int start, int end) {
        return start == end ? String.valueOf(start) : start + "->" + end;
    }
}
