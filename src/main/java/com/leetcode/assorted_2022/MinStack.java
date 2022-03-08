package com.leetcode.assorted_2022;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MinStack {
    private final List<Item> data = new ArrayList<>();

    public MinStack() {
    }

    public void push(int val) {
        Item element;
        if (data.size() == 0) {
            element = new Item(val, val);
        } else {
            element = new Item(val, Math.min(val, data.get(data.size() - 1).min()));
        }
        data.add(element);
    }

    public void pop() {
        data.remove(data.size() - 1);
    }

    public int top() {
        return data.get(data.size() - 1).value();
    }

    public int getMin() {
        return data.get(data.size() - 1).min();
    }

    @Test
    public void test() {
        var stack = new MinStack();
        stack.push(1);
        Assert.assertEquals(stack.top(), 1);
        stack.pop();
        stack.push(-1);
        Assert.assertEquals(stack.top(), -1);
        Assert.assertEquals(stack.getMin(), -1);
        stack.push(-2);
        Assert.assertEquals(stack.top(), -2);
        Assert.assertEquals(stack.getMin(), -2);
        stack.push(1);
        Assert.assertEquals(stack.top(), 1);
        Assert.assertEquals(stack.getMin(), -2);
    }

    private record Item(int value, int min) {
    }
}
