package org.sherman.interview.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author sherman
 * @since 02/04/2016
 */
public class BitsTest {
    private static final Logger log = LoggerFactory.getLogger(BitsTest.class);

    @Test
    public void countNonZeroBits() {
        assertEquals(Bits.countNonZeroBits(100), 3);
    }

    @Test
    public void test() {
        assertEquals(Bits.getCompliment(100), 27);
        assertEquals(Bits.getCompliment(50), 13);
    }
}
