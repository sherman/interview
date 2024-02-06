package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaxPathSumInBinaryTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);
    private static final Holder holder = new Holder();

    public static int maxPathSum(BinaryTree tree) {
        holder.value = tree.value;
        dfs(tree);
        return holder.value;
    }

    private static int dfs(BinaryTree node) {
        if (node == null) {
            return 0;
        }

        var left = dfs(node.left);
        var right = dfs(node.right);

        left = Math.max(left, 0);
        right = Math.max(right, 0);

        holder.value = Math.max(holder.value, left + right + node.value);

        return node.value + Math.max(left, right);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    private static class Holder {
        int value;
    }

    @Test
    public void test1() {
        var node = new BinaryTree(1);
        node.left = new BinaryTree(-5);
        node.right = new BinaryTree(3);
        node.left.left = new BinaryTree(6);
        Assert.assertEquals(maxPathSum(node), 6);
    }

}
