package org.sherman.interview.misc;

import com.google.common.primitives.Ints;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Denis Gabaydulin
 * @since 18.10.16
 */
public class SubsetSum {
    private static final Logger log = LoggerFactory.getLogger(SubsetSum.class);

    private SubsetSum() {
    }

    public static int[] getIndexOfSum(@NotNull int[] data, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            int value = sum - data[i];

            Integer saved = map.get(value);
            if (value >= 0 && saved != null) {
                return new int[]{saved, i};
            } else {
                map.put(data[i], i);
            }
        }

        return new int[]{};
    }

    public static int[] getShortestSubsetOfSum(@NotNull int[] data, int sum) {
        int currentSum = 0;
        List<Integer> indexes = new ArrayList<>();
        List<Integer> found = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            if (data[i] <= sum) {
                indexes.add(i);
                currentSum += data[i];

                if (currentSum >= sum) {
                    log.info("{} {} {}", indexes, found, currentSum);

                    // remove elements from the beginning
                    int residual = currentSum - sum;
                    while (residual > 0) {
                        int removeElt = data[indexes.get(0)];
                        residual = residual - removeElt;
                        indexes.remove(0);
                        currentSum = currentSum - removeElt;
                    }

                    if (residual == 0 && (found.isEmpty() || indexes.size() < found.size())) {
                        // we've found a new solution!
                        found.clear();
                        found.addAll(indexes);
                    }
                }
            } else {
                indexes.clear();
                currentSum = 0;
            }
        }

        return Ints.toArray(found);
    }

    public static int[] getIndexOfSumInSortedArray(@NotNull int[] data, int sum) {
        int i = 0;
        int j = data.length - 1;
        while (i < j) {
            int candidate = data[i] + data[j];
            if (candidate == sum) {
                return new int[]{i, j};
            } else if (candidate < sum) {
                i++;
            } else {
                j--;
            }
        }

        return new int[]{};
    }
}
