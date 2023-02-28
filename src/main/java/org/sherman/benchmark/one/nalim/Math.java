package org.sherman.benchmark.one.nalim;

import one.nalim.Code;
import one.nalim.Link;
import one.nalim.Linker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Math {
    @Link
    public static native double pow(double base, double power);

    static {
        Linker.linkClass(Math.class);
    }

    @Test
    public void test() {
        Assert.assertEquals(Math.pow(2, 4), 16.0);
    }
}
