package com.leetcode.assorted_2022;

import com.leetcode.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class MergeKSortedPointers {
    private static final Logger logger = LoggerFactory.getLogger(MergeKSortedPointers.class);

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root = null;
        ListNode current = null;

        var pointers = new ListNode[lists.length];

        for (int i = 0; i < lists.length; i++) {
            pointers[i] = lists[i];
        }

        while (true) {
            int minPointer = -1;
            ListNode min = null;

            for (var i = 0; i < pointers.length; i++) {
                var item = pointers[i];
                if (item != null) {
                    if (minPointer == -1 || min.val >= item.val) {
                        minPointer = i;
                        min = item;
                    }
                }
            }

            if (minPointer != -1) {
                // get min
                var node = pointers[minPointer];
                // move next
                pointers[minPointer] = node.next;

                node.next = null;
                if (root == null) {
                    root = node;
                } else {
                    current.next = node;
                }
                current = node;
            } else {
                break;
            }
        }

        while (root != null) {
            logger.info("x: [{}]", root.val);
            root = root.next;
        }

        return root;
    }

    @Test
    public void test() {
        var l1 = new int[] {1, 4, 5};
        var l2 = new int[] {1, 3, 4};
        var l3 = new int[] {2, 6};

        mergeKLists(
            new ListNode[] {
                buildList(l1),
                buildList(l2),
                buildList(l3)
            }
        );
    }

    @Test
    public void test2() {
        var l1 = new int[] {-2, -1, -1, -1};
        var l2 = new int[] {};

        mergeKLists(
            new ListNode[] {
                buildList(l1),
                buildList(l2),
            }
        );
    }

    ListNode buildList(int[] data) {
        if (data.length == 0) {
            return null;
        }

        var root = new ListNode(data[0]);
        var nodeCurrent = root;
        for (int i = 1; i < data.length; i++) {
            var node = new ListNode(data[i]);
            nodeCurrent.next = node;
            nodeCurrent = node;
        }
        return root;
    }
}
