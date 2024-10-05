package com.leetcode.assorted_2024;

import static org.testng.Assert.assertEquals;

import com.leetcode.TreeNode;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class KthSmallestElementBST {
    private static final Logger log = LoggerFactory.getLogger(KthSmallestElementBST.class);

    public static int kthSmallest(TreeNode root, int k) {
        var stack = new Stack<TreeNode>();
        var node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (k != 0) {
            var current = stack.pop();
            k--;

            if (k == 0) {
                return current.val;
            }

            var right = current.right;
            while (right != null) {
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
    }

    @Test
    public void case1() {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(2);
        node.right = new TreeNode(21);

        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        node.right.left = new TreeNode(19);
        node.right.right = new TreeNode(25);

        assertEquals(kthSmallest(node, 2), 2);
    }

    @Test
    public void case2() {
        TreeNode node = new TreeNode(1);

        assertEquals(kthSmallest(node, 1), 1);
    }

    @Test
    public void case3() {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);

        assertEquals(kthSmallest(node, 2), 2);
    }

    @Test
    public void case4() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.right = new TreeNode(4);
        node.left.right = new TreeNode(2);

        assertEquals(kthSmallest(node, 2), 2);
    }
}
