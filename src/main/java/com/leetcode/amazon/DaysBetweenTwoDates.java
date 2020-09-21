package com.leetcode.amazon;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DaysBetweenTwoDates {
    public int daysBetweenDates(String date1, String date2) {
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        if (d1.isAfter(d2)) {
            return (int) d1.minusDays(d2.toEpochDay()).toEpochDay();
        } else {
            return (int) d2.minusDays(d1.toEpochDay()).toEpochDay();
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(daysBetweenDates("2019-06-29", "2019-06-30"), 1);
        Assert.assertEquals(daysBetweenDates("2020-01-15", "2019-12-31"), 15);
    }
}
