package org.sherman.interview.misc;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEqualsNoOrder;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 18.10.16
 */
public class SubsetSumTest {
    @Test
    public void getIndexOfSum() {
        assertEqualsNoOrder(SubsetSum.getIndexOfSum(new int[]{5, 8, 7, 2}, 12), new Integer[]{0, 2});
        assertEqualsNoOrder(SubsetSum.getIndexOfSum(new int[]{1, 1, 1, 1}, 12), new Integer[]{});
        assertEqualsNoOrder(SubsetSum.getIndexOfSum(new int[]{1, 11, 1, 1}, 12), new Integer[]{0, 1});
        assertEqualsNoOrder(SubsetSum.getIndexOfSum(new int[]{1, 2, 3, 4, 8}, 12), new Integer[]{3, 4});
    }
}
