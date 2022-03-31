package com.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindNextValueInBST {
    private static final Logger logger = LoggerFactory.getLogger(FindNextValueInBST.class);

    public Integer getNextValue(TreeNode root, int value) {
        if (root == null) {
            return null;
        }

        var left = getNextValue(root.left, value);
        if (left != null) {
            return left;
        }

        if (root.val > value) {
            logger.info("Value: [{}]", root.val);
            return root.val;
        }

        return getNextValue(root.right, value);
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        logger.info("Value: [{}]", root.val);
        inOrderTraversal(root.right);

    }

    public void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        logger.info("Value: [{}]", root.val);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }

    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        logger.info("Value: [{}]", root.val);
    }

    /**
     * 50
     * /      \
     * 30       80
     * /   \     /   \
     * 20   40   60  100
     * /  \   /  \
     * 10   25  31  42
     */
    @Test
    public void test() {
        var root = TreeBuilder.build(new Integer[] {50, 30, 80, 20, 40, 60, 100, 10, 25, 31, 42});
        Assert.assertEquals(getNextValue(root, 21), Integer.valueOf(25));
        Assert.assertEquals(getNextValue(root, 50), Integer.valueOf(60));
        Assert.assertEquals(getNextValue(root, 30), Integer.valueOf(31));
        Assert.assertEquals(getNextValue(root, 60), Integer.valueOf(80));
        Assert.assertEquals(getNextValue(root, 25), Integer.valueOf(30));
    }
}
