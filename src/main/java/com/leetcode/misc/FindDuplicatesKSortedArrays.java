package com.leetcode.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindDuplicatesKSortedArrays {
    private static final Logger logger = LoggerFactory.getLogger(FindDuplicatesKSortedArrays.class);

    public Set<Integer> findDuplicates(int[][] nums) {
        var buffer = new ArrayList<Integer>();
        var queue = new PriorityQueue<Entry>();
        var totalNonEmptyArrays = 0;

        // add first values from each array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].length > 0) {
                queue.add(new Entry(nums[i][0], i, 0));
                totalNonEmptyArrays++;
            }
        }

        var result = new HashSet<Integer>();

        // track last value for each array
        var min = new HashMap<Integer, Integer>();
        var completed = new HashMap<Integer, Integer>();

        for (var i = 0; !queue.isEmpty(); i++) {
            Entry curr = queue.poll();
            if (!buffer.isEmpty()) {
                if (buffer.getLast().equals(curr.value)) {
                    logger.info("dupe: {}", curr.value);
                    result.add(curr.value);
                }
            }
            buffer.add(curr.value);

            min.put(curr.array, curr.value);
            completed.put(curr.array, curr.index);

            // remove array tracking values when the particular array is done   
            if (completed.get(curr.array) == nums[curr.array].length - 1) {
                min.remove(curr.array);
                totalNonEmptyArrays--;
            }

            logger.info("buffer: {}", buffer);

            if (min.size() == totalNonEmptyArrays) {
                // drop all elements which impossible find dupes for
                var absMin = min.values().stream().mapToInt(v -> v).min().orElse(0);
                buffer.removeIf(elt -> elt < absMin);
            }
            var nextIndex = curr.index + 1;
            if (nextIndex < nums[curr.array].length) {
                queue.add(new Entry(nums[curr.array][nextIndex], curr.array, nextIndex));
            }
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(Set.of(1), findDuplicates(new int[][] {{1, 2}, {1, 3}, {4, 5}, {6, 7}}));
        Assert.assertEquals(Set.of(1, 4), findDuplicates(new int[][] {{1, 2}, {1, 1, 3, 4}, {4, 5}, {6, 7}}));
        Assert.assertEquals(Set.of(1, 4, 5), findDuplicates(new int[][] {{1, 2}, {1, 1, 3, 4}, {4, 5, 5}, {6, 7, 9, 10}}));
    }

    private record Entry(int value, int array, int index) implements Comparable<Entry> {
        @Override
        public int compareTo(@NotNull FindDuplicatesKSortedArrays.Entry o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
