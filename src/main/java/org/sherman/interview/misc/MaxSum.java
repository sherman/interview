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
            if (currentSum + sequence[i] <= 0) {
                currentSum = 0;
                elts.clear();
            } else {
                currentSum += sequence[i];
                elts.add(sequence[i]);
                if (currentSum > maxSum) {
                    max.clear();
                    max.addAll(elts);
                    maxSum = currentSum;
                }
            }
        }

        return max.toArray(new Integer[max.size()]);
    }
}
