package com.leetcode.assorted_2024;

import com.leetcode.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ConvertSortedArrayToBinarySearchTree {
    private static final Logger logger = LoggerFactory.getLogger(ConvertSortedArrayToBinarySearchTree.class);

    public TreeNode sortedArrayToBST(int[] nums) {
        var midIndex = nums.length / 2;
        var midElement = nums[midIndex];
        logger.info("[{}]", midElement);

        var root = new TreeNode(midElement);

        for (var i = nums.length - 1; i > midIndex; i--) {
            logger.info("val: [{}]", nums[i]);
            insert(root, nums[i]);
        }

        for (var i = midIndex - 1; i >= 0; i--) {
            logger.info("val: [{}]", nums[i]);
            insert(root, nums[i]);
        }

        return root;
    }

    void insert(TreeNode node, int next) {
        if (next <= node.val) {
            if (node.left == null) {
                logger.info("Parent: [{}] for: [{}]", node.val, next);
                node.left = new TreeNode(next);
            } else {
                insert(node.left, next);
            }
        } else {
            if (node.right == null) {
                logger.info("Parent: [{}] for: [{}]", node.val, next);
                node.right = new TreeNode(next);
            } else {
                insert(node.right, next);
            }
        }
    }

    @Test
    public void test() {
        var node = new TreeNode(0);
        node.left = new TreeNode(-3);
        node.right = new TreeNode(9);
        node.right.left = new TreeNode(5);
        node.left.left = new TreeNode(-3);
        node.left.left.left = new TreeNode(10);

        //sortedArrayToBST(new int[] {-10, -3, 0, 5, 9});
        sortedArrayToBST(new int[] {0, 1, 2, 3, 4, 5});
    }
}
