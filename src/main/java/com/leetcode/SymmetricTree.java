package com.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 != null && n2 != null && n1.val == n2.val) {
            return (isMirror(n1.left, n2.right) && isMirror(n2.right, n1.left));
        }

        return false;
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(1);
        Assert.assertTrue(isSymmetric(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        Assert.assertTrue(isSymmetric(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.right.right = new TreeNode(3);
        Assert.assertTrue(isSymmetric(node));
    }
}
