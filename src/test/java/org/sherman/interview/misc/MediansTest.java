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
}
