package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Denis Gabaydulin
 * @since 08/11/2016
 */
public class Subsets {
    private static final Logger log = LoggerFactory.getLogger(Subsets.class);

    private Subsets() {
    }

    public static int getLongestConsecutive(@NotNull int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (m.get(nums[i]) != null) {
                continue;
            }

            int v = 1;
            Integer v1 = m.get(nums[i] - 1);
            Integer v2 = m.get(nums[i] + 1);

            if (v2 != null) {
                v = v + v2;
            }

            if (v1 != null) {
                v = v + v1;
            }

            if (v2 != null) {
                m.put(nums[i] + v2, v);
            }

            if (v1 != null) {
                m.put(nums[i] - v1, v);
            }

            m.put(nums[i], v);
        }

        log.info("{}", m);

        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (max < m.get(nums[i])) {
                max = m.get(nums[i]);
            }
        }

        return max;
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
