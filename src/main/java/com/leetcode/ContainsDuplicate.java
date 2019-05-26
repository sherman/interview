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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContainsDuplicate {
    @Test(dataProvider = "cases")
    public void containsDuplicate(int[] data, boolean expected) {
        assertEquals(containsDuplicate(data), expected);
    }

    private boolean containsDuplicate(int[] data) {
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < data.length; i++) {
            if (numbers.contains(data[i])) {
                return true;
            } else {
                numbers.add(data[i]);
            }
        }

        return false;
    }

    @DataProvider
    private Object[][] cases() {
        return new Object[][]{
            {
                new int[]{1, 2, 3, 2, 4, 6}, true
            },

            {
                new int[]{1, 2, 3, 5, 4, 6}, false
            }
        };
    }
}
