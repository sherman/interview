package com.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BSTBuilder {
    public static TreeNode build(int[] sortedArray) {
        if (sortedArray == null || sortedArray.length == 0) {
            return null;
        }

        var root = build(sortedArray, 0, sortedArray.length - 1);

        return root;
    }

    public static TreeNode build(int[] sortedArray, int from, int to) {
        if (from > to) {
            return null;
        }

        var mid = (from + to) / 2;
        var node = new TreeNode(sortedArray[mid]);
        node.left = build(sortedArray, from, mid - 1);
        node.right = build(sortedArray, mid + 1, to);
        return node;
    }

    @Test
    public void test() {
        Assert.assertEquals(build(new int[]{}), null);
        Assert.assertEquals(build(null), null);
        Assert.assertTrue(isTreeEquals(build(new int[]{1}), new TreeNode(1)));

        var root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Assert.assertTrue(isTreeEquals(build(new int[]{1, 2, 3}), root));

        root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);
        root.right.right.right = new TreeNode(10);
        Assert.assertTrue(isTreeEquals(build(new int[]{1, 2, 3, 5, 6, 7, 8, 10}), root));
    }

    private static boolean isTreeEquals(TreeNode left, TreeNode right) {
        var current = false;
        if (left != null && right != null) {
            current = left.val == right.val;
        } else {
            current = left == null && right == null;
        }

        if (!current) {
            return false;
        }

        var l = true;
        if (left != null & right != null) {
            l = isTreeEquals(left.left, right.left);
        }

        var r = true;
        if (left != null & right != null) {
            r = isTreeEquals(left.right, right.right);
        }

        return l && r;
    }
}
