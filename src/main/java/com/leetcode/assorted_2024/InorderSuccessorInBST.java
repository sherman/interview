package com.leetcode.assorted_2024;

import com.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InorderSuccessorInBST {
    private static final Logger logger = LoggerFactory.getLogger(InorderSuccessorInBST.class);
    private List<TreeNode> visited = new ArrayList<>();
    // returns the inorder successor of the Node x in BST (rooted at 'root')
    public TreeNode inorderSuccessor(TreeNode root, TreeNode x) {
        inOrderTraversal(root);

        var found = false;
        for (var node : visited) {
            if (found) {
                return node;
            }
            if (node == x) {
                found = true;
            }
        }
        return null;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            visited.add(node);
            inOrderTraversal(node.right);
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
