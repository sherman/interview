package com.leetcode.assorted_2022;

import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PathSum {
    private static boolean checkSum(TreeNode node, int currentSum, int targetSum) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null) {
            return currentSum + node.val == targetSum;
        }

        if (checkSum(node.left, currentSum + node.val, targetSum)) {
            return true;
        }

        if (checkSum(node.right, currentSum + node.val, targetSum)) {
            return true;
        }

        return false;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        return checkSum(root, 0, targetSum);
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.right.right = new TreeNode(3);
        Assert.assertTrue(hasPathSum(node, 6));
        Assert.assertFalse(hasPathSum(node, 7));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        Assert.assertFalse(hasPathSum(node, 1));

        node = new TreeNode(1);
        Assert.assertTrue(hasPathSum(node, 1));

    }
}
