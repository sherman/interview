package com.leetcode.assorted_2025;

import com.leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        var leafs1 = new ArrayList<Integer>();
        collectLeafs(root1, leafs1);

        var leafs2 = new ArrayList<Integer>();
        collectLeafs(root2, leafs2);

        return leafs1.equals(leafs2);
    }

    private void collectLeafs(TreeNode node, List<Integer> leafs) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leafs.add(node.val);
        } else {
            collectLeafs(node.left, leafs);
            collectLeafs(node.right, leafs);
        }
    }

    @Test
    public void test() {
        var node1 = new TreeNode(2);
        node1.left = new TreeNode(1);
        node1.right = new TreeNode(3);

        var node2 = new TreeNode(2);
        node2.left = new TreeNode(1);
        node2.right = new TreeNode(3);

        Assert.assertEquals(leafSimilar(node1, node2), true);

        node1 = new TreeNode(3);
        node1.left = new TreeNode(5);
        node1.right = new TreeNode(1);
        node1.left.left = new TreeNode(6);
        node1.left.right = new TreeNode(2);
        node1.right.left = new TreeNode(9);
        node1.right.right = new TreeNode(8);
        node1.left.right.left = new TreeNode(7);
        node1.left.right.right = new TreeNode(4);

        node2 = new TreeNode(3);
        node2.left = new TreeNode(5);
        node2.right = new TreeNode(1);
        node1.left.left = new TreeNode(6);
        node1.left.right = new TreeNode(7);
        node2.right.left = new TreeNode(4);
        node2.right.right = new TreeNode(2);
        node2.right.right.left = new TreeNode(9);
        node2.right.right.right = new TreeNode(8);

        Assert.assertEquals(leafSimilar(node1, node2), true);
    }
}
