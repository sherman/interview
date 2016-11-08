package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Gabaydulin
 * @since 08/11/2016
 */
public class Subsets {
    private static final Logger log = LoggerFactory.getLogger(Subsets.class);

    private Subsets() {
    }

    public static List<List<Integer>> getAllSubsetsOptimized(@NotNull List<Integer> data) {
        int power = (int) Math.pow(2, data.size());

        List<List<Integer>> allSubsets = new ArrayList<>();

        for (int i = 0; i < power; i++) {
            List<Integer> subset = new ArrayList<>();
            int k = i;
            int index = 0;

            while (k > 0) {
                log.info("{} {} {} {}", i, k, (k & 1), index);
                if ((k & 1) > 0) {
                    subset.add(data.get(index));
                }
                k >>= 1;
                index++;
            }

            allSubsets.add(subset);
        }

        return allSubsets;
    }

    public static List<List<Integer>> getAllSubsets(@NotNull List<Integer> data) {
        return getAllSubsets(data, 0);
    }

    private static List<List<Integer>> getAllSubsets(@NotNull List<Integer> data, int index) {
        List<List<Integer>> allSubsets;

        if (index == data.size()) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets = getAllSubsets(data, index + 1);
            int elt = data.get(index);

            List<List<Integer>> subsets = new ArrayList<>();
            for (List<Integer> subset : allSubsets) {
                List<Integer> newSubset = new ArrayList<>();
                newSubset.addAll(subset);
                newSubset.add(elt);
                subsets.add(newSubset);
            }
            allSubsets.addAll(subsets);
        }

        return allSubsets;
    }
}
