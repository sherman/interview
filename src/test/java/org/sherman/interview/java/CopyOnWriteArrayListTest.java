package org.sherman.interview.java;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CopyOnWriteArrayListTest {
    @Test
    public void add() {
        CopyOnWriteArrayList<Integer> data = new CopyOnWriteArrayList<>();
        data.add(1);
        Assert.assertEquals(data.getElt(0), Integer.valueOf(1));
        data.add(2);
        data.add(3);
        Assert.assertEquals(data.getElt(0), Integer.valueOf(1));
        Assert.assertEquals(data.getElt(1), Integer.valueOf(2));
        Assert.assertEquals(data.getElt(2), Integer.valueOf(3));
    }
}
