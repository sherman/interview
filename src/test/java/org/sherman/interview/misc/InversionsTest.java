package org.sherman.interview.misc;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author sherman
 * @since 20/11/2016
 */
public class InversionsTest {
    @Test
    public void getCountOfInversions() {
        assertEquals(Inversions.getCountOfInversions(new int[]{4, 3, 1, 2, 5, 7}), 4);
    }
}
