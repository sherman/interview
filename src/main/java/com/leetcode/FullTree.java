package com.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FullTree {
    public boolean isFullTree(TreeNode root) {
        int nodes = traverse(root);
        return isPowerOfTwoOptimized(nodes);
    }

    private int traverse(TreeNode node) {
        if (node == null) {
            return 1;
        } else {
            return traverse(node.left) + traverse(node.right);
        }
    }

    private static boolean isPowerOfTwoOptimized(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(1);
        Assert.assertTrue(isFullTree(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        Assert.assertTrue(isFullTree(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(2);
        node.right.right = new TreeNode(2);
        Assert.assertFalse(isFullTree(node));
    }
}
