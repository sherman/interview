package com.leetcode.assorted_2025;

import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        var left = getDepth(root.left);
        var right = getDepth(root.right);
        return Math.abs(left - right) <= 1;
    }

    private static int getDepth(TreeNode node) {
        if (node == null) {
            return 1;
        }

        var left = getDepth(node.left);
        var right = getDepth(node.right);

        return 1 + Math.max(left, right);
    }

    @Test
    public void test0() {
        Assert.assertTrue(isBalanced(null));
        Assert.assertTrue(isBalanced(new TreeNode(3)));
    }

    @Test
    public void test1() {
        var node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(15);
        node.left.right = new TreeNode(7);

        Assert.assertTrue(isBalanced(node));
    }

    @Test
    public void test2() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(3);
        node.left.left.left = new TreeNode(4);
        node.left.left.right = new TreeNode(4);


        Assert.assertFalse(isBalanced(node));
    }
}
