package org.sherman.interview.misc;

public class BinSearch {
    public static int binSearch(int[] array, int elt) {
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int midIndex = (r + l) / 2;
            int mid = array[midIndex];
            if (mid == elt) {
                return midIndex;
            } else if (mid < elt) {
                l = midIndex + 1;
            } else {
                r = midIndex - 1;
            }
        }

        return -1;
    }
}
