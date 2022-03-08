package com.leetcode.assorted_2022;

import com.leetcode.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SymmetricTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     *     1
     *   2    2
     *  3 4  4 3
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        logger.info("[{}] [{}]", left, right);

        if (left == null && right == null) {
            return true;
        }

        if (left != null && right != null && left.val == right.val) {
            return isMirror(left.left, right.right) && isMirror(right.right, left.left);
        } else {
            return false;
        }
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
