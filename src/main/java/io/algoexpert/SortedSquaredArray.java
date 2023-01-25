package io.algoexpert;

import java.util.ArrayList;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class SortedSquaredArray {
    public static int[] sortedSquaredArray(int[] array) {
        var negative = new ArrayList<Integer>();
        var positive = new ArrayList<Integer>();

        for (var i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                negative.add(array[i] * array[i]);
            } else {
                positive.add(array[i] * array[i]);
            }
        }

        //System.out.println(negative);

        var i = 0;
        var j = negative.size() - 1;
        var k = 0;
        while (j >= 0 && k < positive.size()) {
            if (negative.get(j) < positive.get(k)) {
                array[i] = negative.get(j);
                i++;
                j--;
            } else if (negative.get(j) > positive.get(k)) {
                array[i] = positive.get(k);
                i++;
                k++;
            } else {
                array[i] = negative.get(j);
                i++;
                j--;
                array[i] = positive.get(k);
                i++;
                k++;
            }
        }

        while (j >= 0) {
            array[i] = negative.get(j);
            i++;
            j--;
        }

        while (k < positive.size()) {
            array[i] = positive.get(k);
            i++;
            k++;
        }

        return array;
    }

    @Test
    public void test() {
        ArrayAsserts.assertArrayEquals(sortedSquaredArray(new int[] {-5, -2, 3, 4}), new int[] {4, 9, 16, 25});
    }
}
