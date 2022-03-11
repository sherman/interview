package org.sherman.interview.java;

import com.google.common.collect.ImmutableMap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CopyOnWriteCacheTest {
    @Test
    public void test() {
        var cache = new CopyOnWriteCache(3);
        Assert.assertEquals(cache.getData(new int[] {0, 1, 2}), new long[] {0, 0, 0});
        cache.update(ImmutableMap.of(0, 1L, 1, 1L, 2, 1L));
        Assert.assertEquals(cache.getData(new int[] {0, 1, 2}), new long[] {1, 1, 1});
    }
}
