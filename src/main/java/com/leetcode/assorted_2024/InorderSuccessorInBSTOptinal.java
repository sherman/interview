package com.leetcode.assorted_2024;

import com.leetcode.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InorderSuccessorInBSTOptinal {
    private static final Logger logger = LoggerFactory.getLogger(InorderSuccessorInBSTOptinal.class);
    private TreeNode target;
    private TreeNode next;

    // returns the inorder successor of the Node x in BST (rooted at 'root')
    public TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        this.target = target;
        inOrderTraversal(root);
        return this.next;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            logger.info("[{}]", node);
            if (node.val <= target.val) {
                inOrderTraversal(node.right);
            } else {
                next = node;
                inOrderTraversal(node.left);
            }
        }
    }

    @Test
    public void test() {
        var node = new TreeNode(2);
        node.left = new TreeNode(1);
        node.right = new TreeNode(3);
        Assert.assertEquals(inorderSuccessor(node, node), node.right);


        node = new TreeNode(20);
        node.left = new TreeNode(8);
        node.right = new TreeNode(22);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(12);
        node.left.right.left = new TreeNode(10);
        node.left.right.right = new TreeNode(14);
        Assert.assertEquals(inorderSuccessor(node, node.left), node.left.right.left);
    }
}
