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

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class MergeIntervalsTest {
    @Test
    public void case1() {
        MergeIntervals.Interval x1 = new MergeIntervals.Interval(1, 3);
        MergeIntervals.Interval x2 = new MergeIntervals.Interval(2, 6);
        MergeIntervals.Interval x3 = new MergeIntervals.Interval(8, 10);
        MergeIntervals.Interval x4 = new MergeIntervals.Interval(15, 18);

        List<MergeIntervals.Interval> intervals = MergeIntervals.merge(Lists.newArrayList(x1, x2, x3, x4));
        assertEquals(intervals, ImmutableList.of(new MergeIntervals.Interval(1, 6), new MergeIntervals.Interval(8, 10), new MergeIntervals.Interval(15, 18)));
    }

    @Test
    public void case2() {
        MergeIntervals.Interval x1 = new MergeIntervals.Interval(1, 4);
        MergeIntervals.Interval x2 = new MergeIntervals.Interval(4, 5);

        List<MergeIntervals.Interval> intervals = MergeIntervals.merge(Lists.newArrayList(x1, x2));
        assertEquals(intervals, ImmutableList.of(new MergeIntervals.Interval(1, 5)));
    }

    @Test
    public void case3() {
        MergeIntervals.Interval x1 = new MergeIntervals.Interval(1, 4);
        MergeIntervals.Interval x2 = new MergeIntervals.Interval(2, 3);

        List<MergeIntervals.Interval> intervals = MergeIntervals.merge(Lists.newArrayList(x1, x2));
        assertEquals(intervals, ImmutableList.of(new MergeIntervals.Interval(1, 4)));
    }

    @Test
    public void case4() {
        MergeIntervals.Interval x1 = new MergeIntervals.Interval(2, 3);
        MergeIntervals.Interval x2 = new MergeIntervals.Interval(2, 2);
        MergeIntervals.Interval x3 = new MergeIntervals.Interval(3, 3);
        MergeIntervals.Interval x4 = new MergeIntervals.Interval(1, 3);
        MergeIntervals.Interval x5 = new MergeIntervals.Interval(5, 7);
        MergeIntervals.Interval x6 = new MergeIntervals.Interval(2, 2);
        MergeIntervals.Interval x7 = new MergeIntervals.Interval(4, 6);

        List<MergeIntervals.Interval> intervals = MergeIntervals.merge(Lists.newArrayList(x1, x2, x3, x4, x5, x6, x7));
        assertEquals(intervals, ImmutableList.of(new MergeIntervals.Interval(1, 3), new MergeIntervals.Interval(4, 7)));

    }
}
