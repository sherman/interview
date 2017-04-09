package org.sherman.interview.misc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author Denis Gabaydulin
 * @since 09.04.17
 */
public class CustomSortingTest {
    @Test
    public void sortable() {
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{1, 3, 4, 5, 2, 2, 2, 3}), new int[]{2, 3, 2, 4, 1, 3, 2, 5}));
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{1, 2, 3, 4, 5, 6}), new int[]{2, 5, 3, 4, 1, 6}));
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{1, 2, 3}), new int[]{1, 3, 2}));
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{101, 1, 4, 2}), new int[]{2, 4, 1, 101}));
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{1}), new int[]{1}));
        assertTrue(Arrays.equals(CustomSorting.getSorted(new int[]{2, 1, 4, 3, 6, 5}), new int[]{2, 5, 3, 4, 1, 6}));
    }

    @Test(dataProvider = "unSortableCases", expectedExceptions = IllegalArgumentException.class)
    public void unSortable(int[] unSortableCase) {
        CustomSorting.getSorted(unSortableCase);
    }

    @DataProvider
    public static Object[][] unSortableCases() {
        return new Object[][] {
                {new int[]{1, 1, 1}},
                {new int[]{2, 2}}
        };
    }
}
