package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 09.10.16
 */
public class MediansTest {
    @Test
    public void getMedianOfSortedLists() {
        assertEquals(Medians.getMedianOfSortedLists(new int[]{1, 2, 3}, new int[]{100}), 2.5);
        assertEquals(Medians.getMedianOfSortedLists(new int[]{1, 2, 3}, new int[]{100, 101}), 3.0);
        assertEquals(Medians.getMedianOfSortedLists(new int[]{1, 2}, new int[]{2, 2, 2}), 2.0);
        assertEquals(Medians.getMedianOfSortedLists(new int[]{}, new int[]{1, 2, 3}), 2.0);
    }

    @Test
    public void getNthElement() {
        assertEquals(Medians.getNthElement(new int[]{4, 6, 9, 11}, 0), 4);
        assertEquals(Medians.getNthElement(new int[]{4, 6, 9, 11}, 1), 6);
        assertEquals(Medians.getNthElement(new int[]{4, 6, 9, 11}, 2), 9);
        assertEquals(Medians.getNthElement(new int[]{4, 6, 9, 11}, 3), 11);
        assertEquals(Medians.getNthElement(new int[]{4, 67, 11, 11, 6, 9, 5}, 3), 9);
    }
}
