package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.List;

public class MinStack {
    private final List<Integer> data = new ArrayList<>();
    private final List<Integer> min = new ArrayList<>();

    public MinStack() {
    }

    public void push(int val) {
        data.add(val);
        if (min.size() > 0) {
            var tmp = min.get(min.size() - 1);
            min.add(Math.min(tmp, val));
        } else {
            min.add(val);
        }
    }

    public void pop() {
        check();
        data.remove(data.size() - 1);
        min.remove(min.size() - 1);
    }

    public int top() {
        check();
        return data.get(data.size() - 1);
    }

    public int getMin() {
        check();
        return min.get(min.size() - 1);
    }

    private void check() {
        if (data.isEmpty()) {
            throw new IllegalStateException("Empty stack!");
        }
    }
}
