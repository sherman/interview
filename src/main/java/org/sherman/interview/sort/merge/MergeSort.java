package org.sherman.interview.sort.merge;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Denis Gabaydulin
 * @since 24/09/2015
 */
public class MergeSort {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    private MergeSort() {
    }

    public static long[] sortRecursive(@NotNull long[] data) {
        return _sortRecursive(data);
    }

    public static long[] _sortRecursive(@NotNull long[] data) {
        if (data.length > 1) {
            int mid = data.length / 2;

            long[] left = Arrays.copyOfRange(data, 0, mid);
            long[] right = Arrays.copyOfRange(data, mid, data.length);

            log.info("{} {} {}", mid, left, right);

            long[] res = mergeSorted(
                    sortRecursive(left),
                    sortRecursive(right)
            );

            log.info("res {}", res);

            return res;
        }

        return data;
    }

    public static long[] mergeSorted(@NotNull long[] first, @NotNull long[] second) {
        int i = 0, j = 0, resultSize = 0;

        long[] result = new long[first.length + second.length];

        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                result[resultSize++] = first[i++];
            } else if (first[i] > second[j]) {
                result[resultSize++] = second[j++];
            } else {
                result[resultSize++] = first[i++];

                result[resultSize++] = second[j++];
            }
        }

        while (i < first.length) {
            result[resultSize++] = first[i++];
        }

        while (j < second.length) {
            result[resultSize++] = second[j++];
        }

        // FIXME: replace with Preconditions
        if ((i + j) != resultSize) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}
