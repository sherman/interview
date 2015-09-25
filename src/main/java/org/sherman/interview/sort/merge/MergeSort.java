package org.sherman.interview.sort.merge;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Denis Gabaydulin
 * @since 24/09/2015
 */
public class MergeSort {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class);

    private MergeSort() {
    }

    public static Long[] sortRecursive(@NotNull Long[] data) {
        return _sortRecursive(data);
    }

    public static Long[] sortIterative(@NotNull Long[] data) {
        if (data.length < 2) {
            return data;
        }

        Queue<Long[]> result = new LinkedList<Long[]>();
        for (Long elt : data) {
            result.offer(new Long[]{elt});
        }

        while (result.size() > 1) {
            result.offer(mergeSorted(result.poll(), result.poll()));
        }

        Long[] res = result.poll();

        log.info("res {}", (Object) res);

        return res;
    }

    public static Long[] _sortRecursive(@NotNull Long[] data) {
        if (data.length > 1) {
            int mid = data.length / 2;

            Long[] left = Arrays.copyOfRange(data, 0, mid);
            Long[] right = Arrays.copyOfRange(data, mid, data.length);

            log.info("{} {} {}", mid, left, right);

            Long[] res = mergeSorted(
                    _sortRecursive(left),
                    _sortRecursive(right)
            );

            log.info("res {}", (Object) res);

            return res;
        }

        return data;
    }

    public static Long[] mergeSorted(@NotNull Long[] first, @NotNull Long[] second) {
        int i = 0, j = 0, resultSize = 0;

        Long[] result = new Long[first.length + second.length];

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
