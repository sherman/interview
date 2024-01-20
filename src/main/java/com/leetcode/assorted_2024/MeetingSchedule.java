package com.leetcode.assorted_2024;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingSchedule {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return true;
        }

        var sorted = intervals.stream()
            .sorted(
                Comparator.comparing(interval -> ((Interval) interval).start)
                    .thenComparing(interval -> ((Interval) interval).end)
            )
            .collect(Collectors.toList());

        var current = intervals.get(0);
        for (var i = 1; i < sorted.size(); i++) {
            var interval = intervals.get(i);
            if (interval.end >= current.start && interval.start < current.end) {
                return false;
            }

            current = interval;
        }

        return true;
    }

    @Test
    public void test() {
        Assert.assertEquals(false, canAttendMeetings(List.of(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))));
        Assert.assertEquals(false, canAttendMeetings(List.of(new Interval(0, 30), new Interval(0, 30))));
        Assert.assertEquals(true, canAttendMeetings(List.of(new Interval(0, 30), new Interval(31, 32), new Interval(100, 101))));
        Assert.assertEquals(true, canAttendMeetings(List.of(new Interval(0, 8), new Interval(8, 10))));
    }

    public static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
