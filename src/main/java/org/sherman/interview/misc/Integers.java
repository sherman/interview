package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sherman
 * @since 24.02.17
 */
public class Integers {
    private static final Logger log = LoggerFactory.getLogger(Integers.class);

    private Integers() {
    }

    public static boolean isPalindrome(int value) {
        if (value < 0 || value == 10) {
            return false;
        }

        if (value == 0) {
            return true;
        }

        int length = (int) (Math.log10(value) + 1);

        log.info("Length: {}", length);

        int  v = value;
        for (int i = length - 1; i > 0; i = i - 2) {
            int right = (v % 10);
            int left = right * (int) Math.pow(10, i);
            v = (v - left - right) / 10;

            if (v < 0) {
                return false;
            }
        }

        return v < 10;
    }
}
