package com.leetcode.assorted_2025;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GuessNumberHigherOrLower {
    private final int pick;

    public GuessNumberHigherOrLower() {
        this.pick = 0;
    }

    public GuessNumberHigherOrLower(int pick) {
        this.pick = pick;
    }
    private int guess(int pick) {
        if (this.pick == pick) {
            return 0;
        } else if (this.pick > pick) {
            return 1;
        } else {
            return -1;
        }
    }

    public int guessNumber(int n) {
        var l = 1;
        var r = n;
        var result = -1;
        while (l <= r) {
            var mid = l + (r - l) / 2;
            result = guess(mid);
            if (result == 0) {
                return mid;
            } else if (result == -1) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        };

        return result;
    }

    @Test
    public void test() {
        var game = new GuessNumberHigherOrLower(6);
        Assert.assertEquals(game.guessNumber(10), 6);
    }
}
