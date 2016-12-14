package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 08/11/2016
 */
public class SubsetsTest {
    @Test
    public void getAllSubsets() {
        assertEquals(Subsets.getAllSubsets(ImmutableList.of(1, 2, 3)).size(), (int) Math.pow(2, 3));
    }

    @Test
    public void getAllSubsetsOptimized() {
        assertEquals(Subsets.getAllSubsetsOptimized(ImmutableList.of(1, 2, 3)).size(), (int) Math.pow(2, 3));
    }

    @Test
    public void getLongestConsecutive() {
        assertEquals(Subsets.getLongestConsecutive(new int[]{9, 1, -3, 2, 4, 8, 3, -1, 6, -2, -4, 7}), 4);
        assertEquals(Subsets.getLongestConsecutive(new int[]{2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645}), 3);
        assertEquals(Subsets.getLongestConsecutive(new int[]{1, 2, 0, 1}), 3);
    }
}
