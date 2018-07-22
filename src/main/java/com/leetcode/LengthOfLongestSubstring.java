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

import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> visited = new HashSet<>();

        int i = 0;
        int j = 0;
        int maxLength = 0;

        if (s.length() == 1) {
            return 1;
        }

        while (i < s.length() && j < s.length()) {
            char c = s.charAt(j);
            if (!visited.contains(c)) {
                visited.add(c);
                maxLength = Math.max(maxLength, j - i + 1);
                j++;
            } else {
                visited.remove(s.charAt(i));
                i++;
            }
        }

        return maxLength;
    }
}
