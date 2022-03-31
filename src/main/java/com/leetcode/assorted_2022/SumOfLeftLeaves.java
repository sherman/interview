package com.leetcode.assorted_2022;

import com.leetcode.TreeBuilder;
import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }

        return getNodeSum(root, false);
    }


    private int getNodeSum(TreeNode node, boolean left) {
        int count = 0;
        if (node.right == null && node.left == null && left) {
            count += node.val;
        } else if (node.left != null) {
            count += getNodeSum(node.left, true);
        }
        if (node.right != null) {
            count += getNodeSum(node.right, false);
        }
        return count;
    }

    @Test
    public void test() {
        var root = TreeBuilder.build(new Integer[] {50, 30, 80, 20, 40, 60, 100, 10, 25, 31, 42});
        Assert.assertEquals(sumOfLeftLeaves(root), 101);

        root = TreeBuilder.build(new Integer[] {3, 9, 20, null, null, 15, 7});
        Assert.assertEquals(sumOfLeftLeaves(root), 24);

        root = TreeBuilder.build(new Integer[] {1, 2, 3, 4, 5});
        Assert.assertEquals(sumOfLeftLeaves(root), 4);
    }
}
