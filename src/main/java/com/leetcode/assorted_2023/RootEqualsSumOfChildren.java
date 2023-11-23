package com.leetcode.assorted_2023;

import com.leetcode.TreeNode;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RootEqualsSumOfChildren {
    public boolean checkTree(TreeNode root) {
        return root.val == getValue(root.left) + getValue(root.right);
    }

    private int getValue(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.val;
        }
    }

    @Test
    public void test() {
        var root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        Assert.assertEquals(checkTree(root), true);
    }
}
