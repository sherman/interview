package org.sherman.interview.nth_element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DescendingNthElementTest {
    private static final Logger logger = LoggerFactory.getLogger(DescendingNthElementTest.class);

    @Test
    public void partitionExploration() {
        var data = Arrays.asList(13, 3, 15, 19, 75, 85, 43, 94, 35, 92);
        DescendingNthElement.partition(data, 0, data.size(), 4);
        Assert.assertEquals(List.of(85, 92, 94, 75, 13, 19, 43, 15, 35, 3), data);
    }

    @Test(invocationCount = 100)
    public void nthElementRandom() {
        var random = new Random();
        var size = 10;
        var data = new ArrayList<Integer>(size);
        for (var i = 0; i < size; i++) {
            var value = random.nextInt(100);
            data.add(i, value);
        }
        var n = data.size() / 2;
        logger.info("[{}] [{}]", data, data.get(n));
        DescendingNthElement.nthElement(data, n);
        logger.info("[{}] [{}]", data, data.get(n));
        for (var i = 0; i < n; i++) {
            Assert.assertTrue(data.get(i) >= data.get(n));
        }

        for (var i = n + 1; i < data.size(); i++) {
            Assert.assertTrue(data.get(i) <= data.get(n));
        }
    }
}
