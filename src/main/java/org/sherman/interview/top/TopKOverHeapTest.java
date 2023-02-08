package org.sherman.interview.top;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopKOverHeapTest {
    private static final Logger logger = LoggerFactory.getLogger(TopKOverHeapTest.class);

    @Test
    public void addWithoutPop() {
        var top = new TopKOverHeap<Integer>(4);
        top.add(1);
        top.add(2);
        top.add(3);
        top.add(4);

        var data = new ArrayList<Integer>();
        while (!top.isEmpty()) {
            data.add(top.poll());
        }

        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), data);
    }

    @Test
    public void addWithPop() {
        var top = new TopKOverHeap<Integer>(4);
        top.add(1);
        top.add(2);
        top.add(3);
        top.add(4);
        top.add(5);

        var data = new ArrayList<Integer>();
        while (!top.isEmpty()) {
            data.add(top.poll());
        }

        Assert.assertEquals(Arrays.asList(2, 3, 4, 5), data);
    }
}
