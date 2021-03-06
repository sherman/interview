package org.sherman.interview.misc;

import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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
    public void getCompliment() {
        assertEquals(Bits.getCompliment(100), 27);
        assertEquals(Bits.getCompliment(50), 13);
    }

    @Test
    public void isBitSet() {
        assertTrue(Bits.isBitSet(1, 0));
        assertFalse(Bits.isBitSet(1, 1));
        assertFalse(Bits.isBitSet(36, 0));
        assertTrue(Bits.isBitSet(36, 2));
        assertTrue(Bits.isBitSet(36, 5));
        assertFalse(Bits.isBitSet(36, 6));
    }

    @Test
    public void setBit() {
        assertEquals(Bits.setBit(2, 0), 3);
        assertEquals(Bits.setBit(1, 0), 1);
        assertEquals(Bits.setBit(36, 6), 100);
    }

    @Test
    public void isPalindrome() {
        assertFalse(Bits.isPalindrome(8));
        assertTrue(Bits.isPalindrome(3));
        assertTrue(Bits.isPalindrome(65));
        assertTrue(Bits.isPalindrome(0));
        assertTrue(Bits.isPalindrome(1));
    }

    @Test
    public void getMaxGap() {
        assertEquals(Bits.getMaxGap(9), 2);
        assertEquals(Bits.getMaxGap(272), 4);
    }

    @Test
    public void toByteArray() {
        assertEquals(Ints.toByteArray(11), new byte[]{0, 0, 0, 11});
        assertEquals(Ints.toByteArray(1011), new byte[]{0, 0, 3, -13});
        /**
         * 00000000.00000000.00000000 + 00000000.00000000.00000011.11110011 = 00000000.00000000.00000000.(00000000)
         * 00000000.00000000 + 00000000.00000000.00000011.11110011 = 00000000.00000000.00000000.(00000000)
         * 00000000 + 00000000.00000000.00000011.11110011 = 00000000.00000000.00000000.(00000011)  (3)
         * 00000000.00000000.00000011.(11110011) (243-256) (-13)
         */
    }
}
