package com.leetcode;

/**
 * @author Denis M. Gabaydulin
 * @since 01.07.19
 */
public class GetMaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return getMaxDepth(root);
    }

    private int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = getMaxDepth(node.left);
            int rDepth = getMaxDepth(node.right);
            return Math.max(lDepth, rDepth) + 1;
        }
    }
}
