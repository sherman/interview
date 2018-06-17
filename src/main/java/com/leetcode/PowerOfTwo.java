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

public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        int bits = getNumberOfBits(n);

        for (int i = bits; i >= 0; i--) {
            if (i == bits) {
                if (!isBitSet(n, i)) {
                    return false;
                }
            } else {
                if (isBitSet(n, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isBitSet(int n, int bit) {
        return (n & (1 << bit)) != 0;
    }

    private static int getNumberOfBits(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}
