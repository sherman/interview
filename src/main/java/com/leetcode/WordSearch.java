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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordSearch {
    private static final Logger log = LoggerFactory.getLogger(WordSearch.class);

    private static boolean[][] visited;

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * For example,
     * Given board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * word = "ABCCED", -> returns true,
     * word = "SEE", -> returns true,
     * word = "ABCB", -> returns false.
     */

    public static boolean exist(@NotNull char[][] board, @NotNull String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                log.info("iteration");
                visited = new boolean[board.length][board[i].length];

                if (word.toCharArray()[0] == board[i][j] && exist(board, 0, word, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean exist(char[][] board, int index, String word, int i, int j) {
        log.info("{} {} {} {}", index, word.toCharArray()[index], i, j);

        if (visited[i][j]) {
            return false;
        }

        if (word.length() - 1 == index && board[i][j] == word.toCharArray()[index]) {
            return true;
        }

        if (board[i][j] != word.toCharArray()[index]) {
            return false;
        } else {
            visited[i][j] = true;

            // left
            if (j - 1 >= 0) {
                log.info("left");
                if (exist(board, index + 1, word, i, j - 1)) {
                    return true;
                }
            }

            // right
            if (j + 1 < board[i].length) {
                log.info("right");
                if (exist(board, index + 1, word, i, j + 1)) {
                    return true;
                }
            }

            // up
            if (i - 1 >= 0) {
                log.info("up");
                if (exist(board, index + 1, word, i - 1, j)) {
                    return true;
                }
            }

            // bottom
            if (i + 1 < board.length) {
                log.info("bottom");
                if (exist(board, index + 1, word, i + 1, j)) {
                    return true;
                }
            }

            visited[i][j] = false;

            return false;
        }
    }
}
