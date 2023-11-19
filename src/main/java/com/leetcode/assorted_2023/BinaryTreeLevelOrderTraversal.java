package com.leetcode.assorted_2023;

import com.leetcode.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    private static final Logger logger = LoggerFactory.getLogger(BinaryTreeLevelOrderTraversal.class);

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        //logger.info("[{}]", root.val);
        var values = new ArrayList<List<Integer>>();
        values.add(List.of(root.val));
        helper(values, List.of(root));
        logger.info("[{}]", values);
        return values;
    }

    public List<List<Integer>> levelOrderQueue(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        var values = new ArrayList<List<Integer>>();

        var queue = new LinkedList<List<TreeNode>>();
        queue.push(List.of(root));

        while (!queue.isEmpty()) {
            var nodes = queue.pop();

            var items = new ArrayList<Integer>();
            var next = new ArrayList<TreeNode>();
            for (var node : nodes) {
                // add new values to result
                items.add(node.val);

                // add nodes to next level
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            if (!items.isEmpty()) {
                values.add(items);
                queue.push(next);
            }
        }

        return values;
    }

    public void helper(List<List<Integer>> values, List<TreeNode> current) {
        var sub = new ArrayList<Integer>();
        var nodes = new ArrayList<TreeNode>();

        for (var node : current) {
            if (node.left != null) {
                sub.add(node.left.val);
                nodes.add(node.left);
            }

            if (node.right != null) {
                sub.add(node.right.val);
                nodes.add(node.right);
            }
        }

        if (!sub.isEmpty()) {
            values.add(sub);
        }

        if (!nodes.isEmpty()) {
            helper(values, nodes);
        }

        logger.info("[{}]", sub);
    }

    @Test
    public void test1() throws InterruptedException {
        var root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Assert.assertEquals(levelOrder(root), List.of(List.of(3), List.of(9, 20), List.of(15, 7)));
        Assert.assertEquals(levelOrderQueue(root), List.of(List.of(3), List.of(9, 20), List.of(15, 7)));

        Thread.sleep(100);
    }

    @Test
    public void test2() throws InterruptedException {
        var root = new TreeNode(1);

        Assert.assertEquals(levelOrder(root), List.of(List.of(1)));

        Thread.sleep(100);
    }

    @Test
    public void test3() throws InterruptedException {
        Assert.assertEquals(levelOrder(null), List.of());
        Assert.assertEquals(levelOrderQueue(null), List.of());

        Thread.sleep(100);
    }

    @Test
    public void test4() throws InterruptedException {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Assert.assertEquals(levelOrder(root), List.of(List.of(1), List.of(2, 3), List.of(4, 5)));
        Assert.assertEquals(levelOrderQueue(root), List.of(List.of(1), List.of(2, 3), List.of(4, 5)));

        Thread.sleep(100);
    }

    @Test
    public void test5() throws InterruptedException {
        var root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Assert.assertEquals(levelOrder(root), List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)));
        Assert.assertEquals(levelOrderQueue(root), List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)));

        Thread.sleep(100);
    }
}
