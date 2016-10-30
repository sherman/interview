package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 09.10.16
 */
public class Medians {
    private static final Logger log = LoggerFactory.getLogger(Medians.class);

    private Medians() {
    }

    public static double getMedianOfSortedLists(@NotNull int[] one, @NotNull int[] two) {
        int oneLength = one.length;
        int twoLength = two.length;

        int[] result = new int[oneLength + twoLength];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < oneLength && j < twoLength) {
            if (one[i] < two[j]) {
                result[k++] = one[i++];
            } else if (one[i] > two[j]) {
                result[k++] = two[j++];
            } else {
                result[k++] = one[i++];
                result[k++] = two[j++];
            }
        }

        while (i < oneLength) {
            result[k++] = one[i++];
        }

        while (j < twoLength) {
            result[k++] = two[j++];
        }

        return result.length % 2 != 0
                ? result[result.length / 2]
                : (result[result.length / 2] + result[result.length / 2 - 1]) / 2.0;
    }

    public static int getNthElement(@NotNull int[] data, int n) {
        return _getNthElement(data, 0, data.length - 1, n);
    }

    private static int _getNthElement(int[] data, int left, int right, int n) {
        log.info("l: {}, r: {}, data: {}", left, right, data);

        // single element list case
        if (left == right) {
            return data[left];
        }

        int pivotIndex = partition(data, left, right);

        //log.info("p: {}", pivotIndex);

        if (pivotIndex == n) {
            return data[pivotIndex];
        } else if (n < pivotIndex) {
            return _getNthElement(data, left, pivotIndex - 1, n);
        } else {
            return _getNthElement(data, pivotIndex + 1, right, n);
        }
    }

    private static int partition(int[] data, int left, int right) {
        int pivotIndex = (left + right) / 2;

        int pivot = data[pivotIndex];

        //log.info("pivot: {}", pivot);
        //log.info("data: {}", data);

        // move pivot to the right
        swap(data, pivotIndex, right);

        //log.info("data: {}", data);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (data[i] < pivot) {
                //log.info("{}, {}, data: {}", i, data[i], data);
                swap(data, storeIndex, i);
                storeIndex++;
            }
        }

        // move pivot to it's final place
        swap(data, right, storeIndex);

        return storeIndex;
    }

    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
    }
}
