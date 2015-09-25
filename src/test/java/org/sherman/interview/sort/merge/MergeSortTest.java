package org.sherman.interview.sort.merge;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author Denis Gabaydulin
 * @since 24/09/2015
 */
public class MergeSortTest {
    private static final Logger log = LoggerFactory.getLogger(MergeSortTest.class);

    @Test
    public void sortRecursive() {
        long[] result = MergeSort.sortRecursive(new long[]{43, 1, 3, 44, 42});
        log.info("{}", result);

        assertTrue(Arrays.equals(result, new long[]{1, 3, 42, 43, 44}));
    }

    @Test(dataProvider = "mergeSortedCases")
    public void mergeSorted(@NotNull long[] first, @NotNull long[] second, @NotNull long[] expected) {
        long[] result = MergeSort.mergeSorted(first, second);
        log.info("{}", result);

        assertTrue(Arrays.equals(result, expected));
    }

    @DataProvider
    private static Object[][] mergeSortedCases() {
        return new Object[][]{
                {
                        new long[]{}, new long[]{}, new long[]{}
                },

                {
                        new long[]{1}, new long[]{}, new long[]{1}
                },

                {
                        new long[]{}, new long[]{1}, new long[]{1}
                },

                {
                        new long[]{1}, new long[]{1}, new long[]{1, 1}
                },

                {
                        new long[]{1, 2}, new long[]{1}, new long[]{1, 1, 2}
                },

                {
                        new long[]{41, 42}, new long[]{1, 2}, new long[]{1, 2, 41, 42}
                },

        };
    }
}
