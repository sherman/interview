package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sherman
 * @since 02/04/2016
 */
public class Bits {
    private static final Logger log = LoggerFactory.getLogger(Bits.class);

    private Bits() {
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
}
