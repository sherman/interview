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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    private static final Logger log = LoggerFactory.getLogger(MergeIntervals.class);

    /**
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     */
    public static List<Interval> merge(List<Interval> intervals) {
        intervals
            .sort(
                Comparator
                    .comparing(interval -> ((Interval) interval).start)
                    .thenComparing(interval -> ((Interval) interval).end)
            );

        List<Interval> result = new ArrayList<>();

        Interval current = null;
        for (int i = 0; i < intervals.size(); i++) {
            log.info("Current: {}", current);

            if (current == null) {
                current = intervals.get(i);

                if (i == intervals.size() - 1) {
                    result.add(current);
                }
            } else {
                Interval interval = intervals.get(i);
                if (current.end >= interval.start) {
                    Interval newInterval = new Interval(Math.min(current.start, interval.start), Math.max(current.end, interval.end));
                    current = newInterval;

                    if (i == intervals.size() - 1) {
                        result.add(newInterval);
                    }
                } else {
                    result.add(current);
                    current = interval;

                    if (i == intervals.size() - 1) {
                        result.add(current);
                    }
                }
            }
        }

        return result;
    }

    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }


        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("start", start)
                .add("end", end)
                .toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval that = (Interval) o;

            return Objects.equal(this.start, that.start) &&
                Objects.equal(this.end, that.end);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(start, end);
        }
    }
}

