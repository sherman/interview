package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 05/12/2016
 */
public class MissingNumbersTest {
    @Test
    public void findMissingPositiveNumber() {
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{0, -1, 3, 1}), 2);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1, 1}), 2);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{3, 4, -1, 1}), 2);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{0, 1, 2}), 3);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1, -1, -2, 3, 2, 4, 5, 6, 8}), 7);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1, 4, 2}), 3);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{0, 1, 2}), 3);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1, 2, 0, -1}), 3);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{}), 1);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{0}), 1);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1}), 2);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{2}), 1);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{1, 0}), 2);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{2}), 1);
        assertEquals(MissingNumbers.findMissingPositiveNumber(new int[]{2, 1}), 3);
    }

    @Test
    public void findRemoved() {
        assertEquals(MissingNumbers.findRemoved(new int[]{2}), 1);
        assertEquals(MissingNumbers.findRemoved(new int[]{1, 3}), 2);
        assertEquals(MissingNumbers.findRemoved(new int[]{1, 2, 3, 5}), 4);
    }
}
