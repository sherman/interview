package com.leetcode.assorted_2025;

import com.leetcode.TreeNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryTreeRightSideView {

    private static final Logger logger = LoggerFactory.getLogger(BinaryTreeRightSideView.class);

    public List<Integer> rightSideView(TreeNode root) {
        var result = new HashMap<Integer, Integer>();
        traverse(root, result, 0);
        return List.copyOf(result.values());
    }

    private void traverse(TreeNode node, Map<Integer, Integer> result, int level) {
        if (node == null) {
            return;
        }

        //logger.info("{} {}", level, node);
        result.put(level, node.val);
        traverse(node.left, result, level + 1);
        traverse(node.right, result, level + 1);

    }

    @Test
    public void test1() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(4);

        Assertions.assertEquals(List.of(1, 3, 4), rightSideView(node));
    }

    @Test
    public void test2() {
        var node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.left.left = new TreeNode(5);

        Assertions.assertEquals(List.of(1, 3, 4, 5), rightSideView(node));
    }

}
