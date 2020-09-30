package org.sherman.interview;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NativeFunctionsTest {
    @Test
    public void add() {
        Assert.assertEquals(NativeFunctions.add(1, 2), 3);
    }

    @Test
    public void fibonacci() {
        Assert.assertEquals(NativeFunctions.fibonacci(10), 55);
    }
}
