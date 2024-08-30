package com.leetcode.assorted_2024;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TicTacToe {
    private static final Logger logger = LoggerFactory.getLogger(TicTacToe.class);

    private final int[][] board;
    private final Map<Integer, int[]> rows = new HashMap<>();
    private final Map<Integer, int[]> cols = new HashMap<>();

    private final Map<Integer, Integer> leftTop = new HashMap<>();
    private final Map<Integer, Integer> leftBottom = new HashMap<>();

    private final Set<Pair> counterDiagonalCoordinates = new HashSet<>();

    public TicTacToe(int n) {
        board = new int[n][n];

        var j = n - 1;
        var i = 0;
        for (var k = 0; k < n ; k++) {
            counterDiagonalCoordinates.add(new Pair(i, j));
            j--;
            i++;
        }
    }

    public int move(int row, int col, int player) {
        board[row][col] = player;

        var rowsStat = rows.computeIfAbsent(player, ignored -> new int[board.length]);
        rowsStat[row]++;
        var colsStat = cols.computeIfAbsent(player, ignored -> new int[board.length]);
        colsStat[col]++;

        if (rowsStat[row] == board.length) {
            return player;
        }

        if (colsStat[row] == board.length) {
            return player;
        }

        if (rowsStat[col] == board.length) {
            return player;
        }

        if (colsStat[col] == board.length) {
            return player;
        }

        if (isPrincipalDiagonal(row, col)) {
            var val = leftTop.getOrDefault(player, 0);
            leftTop.put(player, val + 1);

            if (val + 1 == board.length) {
                return player;
            }
        }

        if (isCounterDiagonal(row, col)) {
            var val = leftBottom.getOrDefault(player, 0);
            leftBottom.put(player, val + 1);

            if (val + 1 == board.length) {
                return player;
            }
        }

        return 0;
    }

    private boolean isPrincipalDiagonal(int row, int col) {
        return row == col;
    }
    private boolean isCounterDiagonal(int row, int col) {
        return counterDiagonalCoordinates.contains(new Pair(row, col));
    }


    @Test
    public static void test1() {
        var t = new TicTacToe(3);
        t.move(0, 0, 1);
        t.move(0, 2, 2);
        t.move(2, 2, 1);
        t.move(1, 1, 2);
        t.move(2, 0, 1);
        t.move(1, 0, 2);
        Assert.assertEquals(t.move(2, 1, 1), 1);
    }

    @Test
    public static void test2() {
        var t = new TicTacToe(3);
        t.move(0, 0, 1);
        t.move(1, 1, 1);
        Assert.assertEquals(t.move(2, 2, 1), 1);
    }

    @Test
    public static void test3() {
        var t = new TicTacToe(4);
        t.move(0, 3, 1);
        t.move(3, 3, 2);
        t.move(3, 0, 1);
        t.move(0, 0, 2);
        t.move(2, 1, 1);
        Assert.assertEquals(t.move(1, 2, 1), 1);
    }

    @Test
    public static void test4() {
        var t = new TicTacToe(3);
        t.move(0, 0, 1);
        t.move(0, 2, 2);
        t.move(1, 1, 1);
        t.move(2, 2, 2);
        t.move(1, 2, 1);
        t.move(1, 0, 2);
        t.move(0, 1, 1);
        t.move(2, 1, 2);
        t.move(2, 0, 1);
        logger.info("ffe");
    }

    record Pair(int i, int j) {
    }
}
