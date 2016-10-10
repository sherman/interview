package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Gabaydulin
 * @since 10/10/2016
 */
public class MaxSum {
    private MaxSum() {
    }

    public static Integer[] getMaxSum(@NotNull int[] sequence) {
        int maxSum = 0;
        int currentSum = 0;
        List<Integer> elts = new ArrayList<>();
        List<Integer> max = new ArrayList<>();

        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] >= 0) {
                elts.add(sequence[i]);
                currentSum += sequence[i];
            } else {
                if (currentSum >= maxSum) {
                    max.clear();
                    max.addAll(elts);
                    maxSum = currentSum;
                }
                currentSum = 0;
                elts.clear();
            }
        }

        if (max.isEmpty()) {
            return elts.toArray(new Integer[elts.size()]);
        }

        if (currentSum > maxSum) {
            return elts.toArray(new Integer[elts.size()]);
        }

        return max.toArray(new Integer[max.size()]);
    }
}
