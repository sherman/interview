package com.leetcode;

import com.google.common.base.MoreObjects;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("val", val)
            .toString();
    }
}
