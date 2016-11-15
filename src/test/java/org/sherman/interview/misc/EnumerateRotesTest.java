package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 15/11/2016
 */
public class EnumerateRotesTest {
    @Test
    public void enumerateRoutesBfs() {
        assertEquals(EnumerateRotes.enumerateRoutesBfs(ImmutableMap.of(1, ImmutableList.of(2, 3), 2, ImmutableList.of(4, 5), 3, ImmutableList.of(5)), 1, 5), 2f);
    }
}
