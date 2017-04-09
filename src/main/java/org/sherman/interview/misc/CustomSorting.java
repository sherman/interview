package org.sherman.interview.misc;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis Gabaydulin
 * @since 09.04.17
 */
public class CustomSorting {
    private static final Logger log = LoggerFactory.getLogger(CustomSorting.class);

    private CustomSorting() {
    }

    /**
     * Given an unsorted array, sort it in the following order:
     * a[0] < a[1] > a[2] < a[3] > a[4]...
     * Example:
     * 1 3 4 5 2 2 2 3 => 1 5 2 4 2 3 1 2
     */
    public static int[] getSorted(@NotNull int[] data) {
        double median = getMedian(data);

        int lessElt = 0;
        int moreElt = 1;
        for (int i = 0; i < data.length; i++) {
            int elt = data[i];

            if (elt > median && !isOdd(i) && moreElt < data.length) {
                int tmp = data[moreElt];
                data[moreElt] = elt;
                data[i] = tmp;
                moreElt += 2;
            }

            if (elt < median && isOdd(i) && lessElt < data.length) {
                int tmp = data[lessElt];
                data[lessElt] = elt;
                data[i] = tmp;
                lessElt += 2;
            }
        }

        for (int i = 0; i < data.length - 1; i++) {
            if (!isOdd(i)) {
                Preconditions.checkArgument(data[i] < data[i + 1]);
            } else {
                Preconditions.checkArgument(data[i] > data[i + 1]);
            }
        }

        return data;
    }

    private static boolean isOdd(int value) {
        return (value & 1) == 1;
    }

    private static double getMedian(@NotNull int[] data) {
        if (isOdd(data.length)) {
            return Medians.getNthElement(data, data.length / 2);
        } else {
            return (Medians.getNthElement(data, data.length / 2 - 1) + Medians.getNthElement(data, data.length / 2)) / 2.0;
        }
    }
}
