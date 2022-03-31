package com.leetcode.assorted_2022;

import com.leetcode.TreeBuilder;
import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        swapNodes(root);
        return root;
    }

    public void swapNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        var left = root.left;
        var right = root.right;

        var node = new TreeNode(root.val, right, left);
        root.left = node.left;
        root.right = node.right;

        swapNodes(node.left);
        swapNodes(node.right);
    }

    @Test
    public void test() {
        var root = TreeBuilder.build(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        var inverted = invertTree(root);
        Assert.assertEquals(inverted.left.val, 3);
        Assert.assertEquals(inverted.right.val, 2);
        Assert.assertEquals(inverted.left.left.val, 7);
        Assert.assertEquals(inverted.left.right.val, 6);
        Assert.assertEquals(inverted.right.left.val, 5);
        Assert.assertEquals(inverted.right.right.val, 4);
    }
}
