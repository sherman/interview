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
}
