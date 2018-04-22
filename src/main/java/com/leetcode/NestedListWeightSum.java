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

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NestedListWeightSum {
    private static final Logger log = LoggerFactory.getLogger(NestedListWeightSum.class);

    public static int getSum(List<NestedInteger> values, int level) {
        int sum = 0;
        for (NestedInteger value : values) {
            if (value.isInteger()) {
                sum += level * value.getInteger();
            } else {
                sum += getSum(value.getList(), level + 1);
            }
        }
        return sum;
    }

    interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    static class Int implements NestedInteger {
        private final int value;

        Int(int value) {
            this.value = value;
        }

        @Override
        public boolean isInteger() {
            return true;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public List<NestedInteger> getList() {
            return ImmutableList.of();
        }
    }

    static class Ints implements NestedInteger {
        private final List<NestedInteger> ints;

        Ints(List<NestedInteger> ints) {
            this.ints = ints;
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public List<NestedInteger> getList() {
            return ints;
        }
    }
}
