package com.leetcode.assorted_2022;

import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximumDepthOfBinaryTree {
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @Test
    public void test() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.right.right = new TreeNode(3);
        Assert.assertEquals(maxDepth(node), 3);
    }
}
