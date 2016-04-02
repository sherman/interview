package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author sherman
 * @since 02/04/2016
 */
public class BitsTest {
    @Test
    public void countNonZeroBits() {
        assertEquals(Bits.countNonZeroBits(100), 3);
    }
}
