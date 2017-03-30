package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author sherman
 * @since 02/04/2016
 */
public class Bits {
    private static final Logger log = LoggerFactory.getLogger(Bits.class);

    private Bits() {
    }

    public static boolean isBitSet(int number, int n) {
        return (number & (1 << n)) != 0;
    }

    public static int setBit(int number, int n) {
        return number | (1 << n);
    }

    public static int countNonZeroBits(int value) {
        if (value < 0) {
            throw new UnsupportedOperationException("Value must be positive!");
        }

        int count = 0;
        while (value != 0) {
            count += value & 1;
            value = value >> 1;
            log.info("{}, {}", value, decToBin(value));
        }
        return count;
    }

    public static int getCompliment(int value) {
        int count = 0;
        int original = value;
        while (original != 0) {
            count++;
            original = original >> 1;
        }

        return ~value & ((1 << count) - 1);
    }

    public static String decToBin(int decimal) {
        StringBuilder result = new StringBuilder();

        int x;
        do {
            x = decimal / 2;
            decimal = decimal - (x * 2);
            result.append(decimal);
            decimal = x;
        } while (decimal != 0);

        return result.reverse().toString();
    }

    /**
     * @return true in case of a value is palindrome in a binary representation
     */
    public static boolean isPalindrome(int value) {
        int original = value;
        int count = 0;
        while (original != 0) {
            count++;
            original = original >> 1;
        }
        log.info("{} {}", count, decToBin(value));

        for (int i = 0; i < count / 2; i++) {
            boolean left = (value & (1 << i)) != 0;
            boolean right = (value & (1 << (count - i - 1))) != 0;

            if (left != right) {
                return false;
            }

        }

        return true;
    }

    /**
     * Problem: Get maximum binary Gap.
     * For example, 9's binary form is 1001, the gap is 2.
     */
    public static int getMaxGap(int value) {
        int bits = 0;
        int original = value;
        while (value != 0) {
            value = value >> 1;
            bits++;
        }

        log.info("{}", bits);

        int gap = 0;
        int current = 0;
        for (int i = 0; i < bits; i++) {
            if ((original & (1 << i)) != 0) {
                if (current > gap) {
                    gap = current;
                }
                current = 0;
            } else {
                current++;
            }
        }

        if (current > gap) {
            gap = current;
        }

        return gap;
    }
}