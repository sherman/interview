package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Denis Gabaydulin
 * @since 18.10.16
 */
public class SubsetSum {
    private static final Logger log = LoggerFactory.getLogger(SubsetSum.class);

    private SubsetSum() {
    }

    public static Integer[] getIndexOfSum(@NotNull int[] array, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int value = sum - array[i];

            Integer saved = map.get(value);
            if (value >= 0 && saved != null) {
                return new Integer[]{saved, i};
            } else {
                map.put(array[i], i);
            }
        }

        return new Integer[]{};
    }
}
