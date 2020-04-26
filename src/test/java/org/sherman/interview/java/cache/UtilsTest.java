package org.sherman.interview.java.cache;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UtilsTest {
    @Test
    public void nextPowerOfTwo() {
        Assert.assertEquals(Utils.nextPowerOfTwo(0), 0);
        Assert.assertEquals(Utils.nextPowerOfTwo(3), 4);
        Assert.assertEquals(Utils.nextPowerOfTwo(2), 2);
        Assert.assertEquals(Utils.nextPowerOfTwo(15), 16);
        Assert.assertEquals(Utils.nextPowerOfTwo(Integer.MAX_VALUE), 2147483648L);
    }

    @Test
    public void hash() {
        Assert.assertEquals(Utils.hash(1L), 1);
        Assert.assertEquals(Utils.hash(100L), 100);
        Assert.assertEquals(Utils.hash(11213343L), 11213492);
        Assert.assertEquals(Utils.hash(121212121212L), 953026734);
    }
}
