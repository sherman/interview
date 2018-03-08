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

public class PlusOne {
    /**
     * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * The digits are stored such that the most significant digit is at the head of the list.
     */
    public static int[] plusOne(int[] digits) {
        int transfer = 0;
        int last = digits.length;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                transfer++;
                digits[i] = 0;
            } else {
                digits[i] = digits[i] + 1;
                break;
            }
            last = i;
        }


        if (last == 0 && transfer > 0) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 1, result, 1, digits.length - 1);
            return result;
        } else {
            return digits;
        }
    }
}
