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

import java.util.*;

import static java.util.Comparator.reverseOrder;

public class MedianFinder {
    private static final Logger log = LoggerFactory.getLogger(MedianFinder.class);

    private PriorityQueue<Integer> low = new PriorityQueue<>();
    private PriorityQueue<Integer> high = new PriorityQueue<>(reverseOrder());
    private int elements = 0;

    public MedianFinder() {
    }

    public void addNum(int num) {
        log.info("Start: {} H: {} L: {}, {}", num, high, low, elements);
        
        high.add(num);

        if (elements % 2 == 0) {
            // just add to high
            if (low.isEmpty()) {
                log.info("Add to high {}", num);
                elements++;
                return;
            } else if (high.peek() > low.peek()) {
                int highRoot = high.poll();
                int lowRoot = low.poll();
                high.add(lowRoot);
                low.add(highRoot);
                log.info("Rebalance: {} {}", highRoot, lowRoot);
            }
        } else {
            log.info("To low: {} {}", high.peek(), low.peek());
            low.add(high.poll());
        }
        elements++;

        log.info("End: {} H: {} L: {}, {}", num, high, low, elements);
    }

    public double findMedian() {
        if (elements % 2 != 0) {
            return (double) getPeek(high);
        } else {
            return (double) (getPeek(high) + getPeek(low)) / 2.0d;
        }
    }

    private int getPeek(PriorityQueue queue) {
        if (queue.peek() == null) {
            return 0;
        } else {
            return (int) queue.peek();
        }
    }
}
