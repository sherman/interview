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
}
