package com.leetcode.assorted_2022;

import com.leetcode.ListNode;
import java.util.PriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class MergeKSorted {
    private static final Logger logger = LoggerFactory.getLogger(MergeKSorted.class);

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root = null;
        ListNode current = null;

        PriorityQueue<ListNode> queue;
        queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        for (int i = 0; i < lists.length; i++) {
            var node = lists[i];
            if (node != null) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            var node = queue.poll();
            if (root == null) {
                root = node;
            } else {
                current.next = node;
            }
            current = node;

            if (node.next != null) {
                queue.offer(node.next);
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
