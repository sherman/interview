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

public class FlipGame {
    /**
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
     * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
     * Write a function to compute all possible states of the string after one valid move.
     * For example, given s = "++++", after one move, it may become one of the following states:
     * [
     * "--++",
     * "+--+",
     * "++--"
     * ]
     */

    public static List<String> getStates(@NotNull String s) {
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == chars[i + 1] && chars[i] == '+') {
                char[] newString = chars.clone();
                newString[i] = '-';
                newString[i + 1] = '-';
                result.add(new String(newString));
            }

            if (chars[i] == chars[i + 1] && chars[i] == '-') {
                char[] newString = chars.clone();
                newString[i] = '+';
                newString[i + 1] = '+';
                result.add(new String(newString));
            }
        }
        return result;
    }
}
