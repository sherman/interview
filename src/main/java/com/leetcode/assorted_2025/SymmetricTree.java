package com.leetcode.assorted_2025;

import com.leetcode.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SymmetricTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 != null && root2 != null && root1.val == root2.val) {
            logger.info(root1.getId() + " " + root2.getId());
            return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
        }

        return false;
    }

    @Test
    public void test() {
        TreeNode node = new TreeNode(1);
        //Assertions.assertTrue(isSymmetric(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        //Assertions.assertTrue(isSymmetric(node));

        node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.right.right = new TreeNode(3);
        Assertions.assertTrue(isSymmetric(node));
    }
}
