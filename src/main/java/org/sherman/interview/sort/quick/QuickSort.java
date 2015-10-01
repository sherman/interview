package org.sherman.interview.sort.quick;

import org.jetbrains.annotations.NotNull;

/**
 * @author Denis Gabaydulin
 * @since 01/10/2015
 */
public class QuickSort {
    private QuickSort() {
    }

    public static long[] sort(@NotNull long[] data) {
        return sort(data, 0, data.length - 1);
    }

    private static long[] sort(@NotNull long[] data, int left, int right) {
        int mid = partition(data, left, right);

        if (left < mid - 1) {
            sort(data, left, mid - 1);
        }

        if (mid < right) {
            sort(data, mid, right);
        }

        return data;
    }

    private static int partition(long[] data, int left, int right) {
        long mid = data[(left + right) / 2];

        int i = left, j = right;

        while (i <= j) {
            while (data[i] < mid) {
                i++;
            }

            while (data[j] > mid) {
                j--;
            }

            if (i <= j) {
                long tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }
}
