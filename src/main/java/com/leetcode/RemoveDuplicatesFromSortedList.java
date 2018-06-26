package com.leetcode;

public class RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;

        while (current != null) {
            if (current.next != null) {
                if (current.val == current.next.val) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            } else {
                break;
            }
        }

        return head;
    }
}
