package com.leetcode;

public class TreeBuilder {
    private TreeBuilder() {
    }

    public static TreeNode build(Integer[] data) {
        TreeNode root = new TreeNode(data[0]);
        build(root, data, 0);
        return root;
    }

    private static void build(TreeNode node, Integer[] data, int index) {
        if (index >= data.length / 2) {
            return;
        }

        if (data[2 * index + 1] != null) {
            node.left = new TreeNode(data[2 * index + 1]);
            build(node.left, data, 2 * index + 1);
        }

        if (data[2 * index + 2] != null) {
            node.right = new TreeNode(data[2 * index + 2]);
            build(node.right, data, 2 * index + 2);
        }
    }
}
