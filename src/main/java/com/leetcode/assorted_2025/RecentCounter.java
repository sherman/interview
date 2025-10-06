package com.leetcode.assorted_2025;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {
    private final Queue<Integer> queue = new LinkedList<>();

    public RecentCounter() {
    }

    public int ping(int t) {
        queue.offer(t);

        while (!queue.isEmpty()) {
            var item = queue.peek();
            if (t - item > 3000) {
                queue.poll();
            } else {
                break;
            }
        }
        return queue.size();
    }
}
