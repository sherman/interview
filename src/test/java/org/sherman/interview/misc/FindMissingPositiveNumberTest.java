package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 05/12/2016
 */
public class FindMissingPositiveNumberTest {
    @Test
    public void findMissingPositiveNumber() {
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{0, -1, 3, 1}), 2);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1, 1}), 2);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{3, 4, -1, 1}), 2);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{0, 1, 2}), 3);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1, -1, -2, 3, 2, 4, 5, 6, 8}), 7);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1, 4, 2}), 3);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{0, 1, 2}), 3);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1, 2, 0, -1}), 3);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{}), 1);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{0}), 1);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1}), 2);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{2}), 1);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{1, 0}), 2);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{2}), 1);
        assertEquals(FindMissingPositiveNumber.findMissingPositiveNumber(new int[]{2, 1}), 3);
    }
}
