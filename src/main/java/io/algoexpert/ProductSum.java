package io.algoexpert;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductSum {
    public static int productSum(List<Object> array) {
        return helper(array, 2);
    }

    private static int helper(List<Object> data, int level) {
        var sum = 0;
        for (var object : data) {
            if (object instanceof List obj) {
                sum += level * helper(obj, level + 1);
            } else {
                sum += (int) object;
            }
        }
        return sum;
    }

    @Test
    public void test() {
        Assert.assertEquals(productSum(List.of(List.of(List.of(5)))), 30);
        Assert.assertEquals(productSum(List.of(1, 2, List.of(3), 4, 5)), 18);
    }
}
