package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 20.05.19
 */
public class Ranges {

    @DataProvider
    private static Object[][] cases() {
        return new Object[][]{
           {
                ImmutableList.of(
                    new Range(0, 1),
                    new Range(2, 4),
                    new Range(6, 9),
                    new Range(3, 5)
                ),
                2
            },

            {
                ImmutableList.of(
                    new Range(0, 1),
                    new Range(2, 4),
                    new Range(6, 9)
                ),
                0
            },

            {
                ImmutableList.of(
                    new Range(0, 1),
                    new Range(1, 2)
                ),
                2
            },

            {
                ImmutableList.of(
                    new Range(1, 3),
                    new Range(3, 1)
                ),
                0
            }
        };
    }

    @Test(dataProvider = "cases")
    public void getRanges(List<Range> ranges, int expected) {
        assertEquals(getRanges(ranges), expected);
    }

    private static int getRanges(List<Range> ranges) {
        // NOTE: I'd like to use RangeSet here for non-overlapped ranges to have O(log n) for searching overlap ranges.
        Set<Range> books = new HashSet<>();
        Set<Range> conflicted = new HashSet<>();

        for (Range range : ranges) {
            // find all conflicts
            Set<Range> conflicts = books.stream()
                .filter(r -> isOverlap(r, range))
                .collect(Collectors.toSet());

            if (!conflicts.isEmpty()) {
                // add all overlapped
                conflicted.addAll(conflicts);
                // add itself
                conflicted.add(range);
            }

            // add range to collection
            books.add(range);
        }

        return conflicted.size();
    }

    private static boolean isOverlap(Range a, Range b) {
        return ((a.open <= b.open && b.open <= a.close)
            || (a.open <= b.close && b.close <= a.close))
            && !(
                a.open == b.close
                && a.close == b.open
            );
    }

    private static class Range {
        final long open;
        final long close;

        Range(long open, long close) {
            this.open = open;
            this.close = close;
        }

        boolean isOverlap(Range range) {
            return (this.open >= range.open && this.close <= range.open) || (this.open >= range.close && this.close <= range.close);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return open == range.open &&
                close == range.close;
        }

        @Override
        public int hashCode() {
            return Objects.hash(open, close);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Range{");
            sb.append("open=").append(open);
            sb.append(", close=").append(close);
            sb.append('}');
            return sb.toString();
        }
    }
}
