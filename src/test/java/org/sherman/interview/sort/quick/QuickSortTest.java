package org.sherman.interview.sort.quick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * @author Denis Gabaydulin
 * @since 01/10/2015
 */
public class QuickSortTest {
    private static final Logger log = LoggerFactory.getLogger(QuickSortTest.class);

    @Test
    public void sort() {
        long[] result = QuickSort.sort(new long[]{43L, 1L, 3L, 44L, 42L});

        assertTrue(Arrays.equals(result, new long[]{1L, 3L, 42L, 43L, 44L}));
    }
}
