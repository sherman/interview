package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import javax.xml.transform.Result;
import java.util.*;

import static org.testng.Assert.assertEquals;

public class DivideNumbers {
    private static final Logger log = LoggerFactory.getLogger(DivideNumbers.class);

    public Result divide(int dividend, int divider) {
        return divide_(dividend, divider);
    }

    private Result divide_(int dividend, int divider) {
        Set<Integer> sequence = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        List<Integer> sequenceIndex = new ArrayList<>();
        int periodicElement = 0;

        // handle an integer part
        int intgr = getInteger(dividend, divider);
        dividend = dividend - (intgr * divider);

        while (true) {
            int temp = dividend;
            if (dividend < divider) {
                temp = dividend * 10;
            }
            int res = temp / divider;
            dividend = temp - (res * divider);
            if (!sequence.contains(temp)) {
                sequence.add(temp);
                sequenceIndex.add(temp);
                result.add(res);
                log.info("{} {}", temp, res);
            } else {
                periodicElement = sequenceIndex.indexOf(temp);
                break;
            }
        }

        Result r = new Result();
        r.periodic = getPeriodic(result, periodicElement);
        r.nonPeriodic = getNonPeriodic(result, periodicElement);
        r.intgr = intgr;
        return r;
    }

    private int getInteger(int dividend, int divider) {
        if (dividend < divider) {
            return 0;
        } else {
            return dividend / divider;
        }
    }

    private List<Integer> getNonPeriodic(List<Integer> result, int periodicElement) {
        return result.subList(0, Math.max(0, periodicElement));
    }

    private List<Integer> getPeriodic(List<Integer> result, int periodicElement) {
        return result.subList(periodicElement, result.size());
    }

    private static class Result {
        int intgr;
        List<Integer> periodic = new ArrayList<>();
        List<Integer> nonPeriodic = new ArrayList<>();
    }

    @Test
    public void case1() {
        Result r = divide(4, 3);
        assertEquals(r.periodic, ImmutableList.of(3));
        assertEquals(r.nonPeriodic, ImmutableList.of());
        assertEquals(r.intgr, 1);
    }

    @Test
    public void case2() {
        Result r = divide(1, 3);
        assertEquals(r.periodic, ImmutableList.of(3));
        assertEquals(r.nonPeriodic, ImmutableList.of());
        assertEquals(r.intgr, 0);
    }

    @Test
    public void case3() {
        Result r = divide(13, 12);
        assertEquals(r.periodic, ImmutableList.of(3));
        assertEquals(r.nonPeriodic, ImmutableList.of(0, 8));
        assertEquals(r.intgr, 1);
    }
}
