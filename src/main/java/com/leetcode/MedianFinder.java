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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFinder {
    private List<Integer> values = new ArrayList<>();
    private Double median = Double.NaN;

    public MedianFinder() {
    }

    public void addNum(int num) {
        values.add(num);
        this.median = Double.NaN;
    }

    public double findMedian() {
        if (median.equals(Double.NaN)) {
            median = calculateMedian();
        }

        return median;
    }

    /**
     * Complexity: O(n * (n * (log n))
     */
    private double calculateMedian() {
        if (values.size() == 1) {
            return values.get(0);
        }

        Collections.sort(values);

        if (values.size() % 2 == 0) {
            return (values.get(values.size() / 2) + values.get(values.size() / 2 - 1)) / 2.0d;
        } else {
            return values.get(values.size() / 2);
        }
    }
}
