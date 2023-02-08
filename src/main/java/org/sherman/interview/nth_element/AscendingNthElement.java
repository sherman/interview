package org.sherman.interview.nth_element;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AscendingNthElement {
    private static final Logger logger = LoggerFactory.getLogger(AscendingNthElement.class);

    public static void nthElement(List<Integer> data, int nth) {
        nthElement(data, 0, data.size(), nth);
    }

    public static void nthElement(List<Integer> data, int low, int high, int nth) {
        for (; ; ) {
            //logger.info("[{}] [{}] [{}]", nth, low, high);
            var k = partition(data, low, high, low + (high - low) / 2);
            //logger.info("[{}], [{}]", low + (high - low) / 2, data);
            if (nth < k) {
                high = k;
            } else if (nth > k) {
                low = k + 1;
            } else {
                return;
            }
        }
    }

    public static int partition(List<Integer> data, int low, int high, int separatorIndex) {
        var i = low;
        var j = high - 1;
        var separator = data.get(separatorIndex);
        //logger.info("[{}]", separator);
        swap(data, i++, separatorIndex);
        while (i <= j) {
            while (i <= j && data.get(i) < separator) {
                i++;
            }

            while (i <= j && data.get(j) > separator) {
                j--;
            }

            if (i <= j) {
                swap(data, i, j);
                i++;
                j--;
            }
        }
        //logger.info("[{}]: [{}] [{}]", data, i, j);
        swap(data, j, low);
        return j;
    }

    private static void swap(List<Integer> data, int i, int j) {
        var t = data.get(j);
        data.set(j, data.get(i));
        data.set(i, t);
    }
}
