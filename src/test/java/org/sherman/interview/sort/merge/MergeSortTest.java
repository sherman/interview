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
        Long[] result = MergeSort.sortRecursive(new Long[]{43L, 1L, 3L, 44L, 42L});
        log.info("{}", (Object) result);

        assertTrue(Arrays.equals(result, new Long[]{1L, 3L, 42L, 43L, 44L}));
    }

    @Test
    public void sortIterative() {
        Long[] result = MergeSort.sortIterative(new Long[]{43L, 1L, 3L, 44L, 42L});
        log.info("{}", (Object) result);

        assertTrue(Arrays.equals(result, new Long[]{1L, 3L, 42L, 43L, 44L}));
    }

    @Test(dataProvider = "mergeSortedCases")
    public void mergeSorted(@NotNull Long[] first, @NotNull Long[] second, @NotNull Long[] expected) {
        Long[] result = MergeSort.mergeSorted(first, second);
        log.info("{}", (Object) result);

        assertTrue(Arrays.equals(result, expected));
    }

    @DataProvider
    private static Object[][] mergeSortedCases() {
        return new Object[][]{
                {
                        new Long[]{}, new Long[]{}, new Long[]{}
                },

                {
                        new Long[]{1L}, new Long[]{}, new Long[]{1L}
                },

                {
                        new Long[]{}, new Long[]{1L}, new Long[]{1L}
                },

                {
                        new Long[]{1L}, new Long[]{1L}, new Long[]{1L, 1L}
                },

                {
                        new Long[]{1L, 2L}, new Long[]{1L}, new Long[]{1L, 1L, 2L}
                },

                {
                        new Long[]{41L, 42L}, new Long[]{1L, 2L}, new Long[]{1L, 2L, 41L, 42L}
                },

        };
    }
}
