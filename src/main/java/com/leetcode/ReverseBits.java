package com.leetcode;

import org.sherman.interview.misc.Bits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseBits {
    private static final Logger log = LoggerFactory.getLogger(ReverseBits.class);

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        log.info("{}", Bits.decToBin(n));

        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }

        log.info("r: {}", Bits.decToBin(n));

        return n;
    }

    private static int swapBits(int n, int l, int r) {
        int a = (n >> l) & 1;
        int b = (n >> r) & 1;

        if ((a ^ b) != 0) {
            n = toggle(n, l);
            n = toggle(n, r);
            return n;
        }

        return n;
    }

    private static int toggle(int n, int bit) {
        return n ^ (1 << bit);
    }
}
