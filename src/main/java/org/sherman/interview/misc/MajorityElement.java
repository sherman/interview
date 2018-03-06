package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;

/**
 * @author Denis M. Gabaydulin
 * @since 06.03.18
 */
public class MajorityElement {
    /**
     * Majority Element: A majority element in an array A[] of size n is an element
     * that appears more than n/2 times (and hence there is at most one such element).
     */
    public static int findMajorityElement(@NotNull int[] data) {
        if (data.length == 0) {
            throw new IllegalArgumentException("Data is empty!");
        }

        int current = data[0];
        int counter = 1;

        for (int i = 1; i < data.length; i++) {
            if (data[i] == current) {
                counter++;
            } else {
                if (counter > 0) {
                    counter--;
                } else {
                    current = data[i];
                    counter = 1;
                }
            }
        }

        return counter;
    }
}
