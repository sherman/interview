package com.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryTreeBasics {
    public int getNumberOfNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + getNumberOfNodes(node.left) + getNumberOfNodes(node.right);
    }

    public int getNumberOfLeafNodes(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }

        var left = 0;
        if (node.left != null) {
            left = getNumberOfLeafNodes(node.left);
        }

        return left + getNumberOfLeafNodes(node.right);
    }

    public int getHeightOfTree(TreeNode node) {
        if (node == null) {
            return 0;
        }

        var left = getHeightOfTree(node.left);
        var right = getHeightOfTree(node.right);

        return 1 + Math.max(left, right);
    }

    @Test
    public void test() {
        var root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        root.right.right.right = new TreeNode(10);

        Assert.assertEquals(getNumberOfNodes(root), 8);
        Assert.assertEquals(getNumberOfLeafNodes(root), 4);
        Assert.assertEquals(getHeightOfTree(root), 4);
    }
}
