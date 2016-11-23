package org.sherman.interview.misc;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Denis Gabaydulin
 * @since 23/11/2016
 */
public class ATM {
    private static final Logger log = LoggerFactory.getLogger(ATM.class);

    private ATM() {
    }

    /**
     * The formula is F(sum) = min(F(sum - a1), F(sum - a2),..., F(sum - ak)) + 1.
     */
    public static List<Integer> charge(@NotNull Set<Integer> bills, int sum) {
        int[] sumsToNumBills = new int[sum + 1];

        for (int i = 1; i <= sum; i++) {
            sumsToNumBills[i] = Integer.MAX_VALUE;
            for (Integer bill : bills) {
                if (i >= bill && sumsToNumBills[i - bill] + 1 < sumsToNumBills[i]) {
                    sumsToNumBills[i] = sumsToNumBills[i - bill] + 1;
                }
            }
        }

        log.info("{}", sumsToNumBills);

        List<Integer> resultBills = new ArrayList<>();

        if (sumsToNumBills[sum] < 0) {
            return resultBills;
        }

        int n = sum;
        while (n > 0) {
            for (Integer bill : bills) {
                if (n - bill == 0 || (sumsToNumBills[n - bill] == sumsToNumBills[n] - 1)) {
                    log.info("{} {}", n, bill);
                    resultBills.add(bill);
                    n -= bill;
                    break;
                }
            }
        }

        return resultBills;
    }
}
