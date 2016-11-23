package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 23/11/2016
 */
public class ATMTest {
    @Test
    public void charge() {
        assertEquals(ATM.charge(ImmutableSet.of(1, 4, 6), 5), ImmutableList.of(1, 4));
        assertEquals(ATM.charge(ImmutableSet.of(2, 4, 6), 3), ImmutableList.of());
        assertEquals(ATM.charge(ImmutableSet.of(1, 4, 6), 15), ImmutableList.of(1, 4, 4, 6));
        assertEquals(ATM.charge(ImmutableSet.of(10, 100, 500), 760), ImmutableList.of(10, 10, 10, 10, 10, 10, 100, 100, 500));
    }
}
