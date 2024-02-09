package com.leetcode.assorted_2024;

import com.leetcode.TreeNode;

public class DiameterOfBinaryTree {
    private int value = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return value;
    }

    public int dfs(TreeNode tree) {
        if (tree == null) {
            return 0;
        }

        var left = dfs(tree.left);
        var right = dfs(tree.right);

        value = Math.max(value, left + right);

        return Math.max(left, right) + 1;
    }
}
