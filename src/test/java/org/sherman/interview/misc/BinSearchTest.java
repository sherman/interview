package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BinSearchTest {
    @Test
    public void test() {
        assertEquals(BinSearch.binSearch(new int[]{}, 1), -1);
        assertEquals(BinSearch.binSearch(new int[]{1}, 1), 0);
        assertEquals(BinSearch.binSearch(new int[]{1,2,3}, 1), 0);
        assertEquals(BinSearch.binSearch(new int[]{1,2,3,9}, 2), 1);
        assertEquals(BinSearch.binSearch(new int[]{1,2,3,9}, 3), 2);
        assertEquals(BinSearch.binSearch(new int[]{1,2,3,9}, 9), 3);
    }
}
