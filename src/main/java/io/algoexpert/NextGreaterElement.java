package io.algoexpert;

import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class NextGreaterElement {
    public int[] nextGreaterElement(int[] array) {
        var result = new int[array.length];

        for (var i = 0; i < array.length; i++) {
            var k = i;
            while (true) {
                k++;

                if (k == array.length) {
                    k = 0;
                }

                if (k == i) {
                    result[i] = -1;
                    break;
                }

                if (array[k] > array[i]) {
                    result[i] = array[k];
                    break;
                }
            }
        }

        return result;
    }

    @Test
    public void test1() {
        ArrayAsserts.assertArrayEquals(
            new int[]{5, 6, 6, 6, 7, -1, 5},
            nextGreaterElement(new int[]{2, 5, -3, -4, 6, 7, 2})
        );
    }
}
