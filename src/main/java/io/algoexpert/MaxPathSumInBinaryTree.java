package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaxPathSumInBinaryTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);

    public static int maxPathSum(BinaryTree tree) {
        var holder1 = new Holder();
        var holder2 = new Holder();
        getSubtreeSum(tree.left, holder1);
        getSubtreeSum(tree.right, holder2);

        if (holder1.sum < 0) {
            holder1.sum = 0;
        }

        if (holder2.sum < 0) {
            holder2.sum = 0;
        }

        return holder1.sum + tree.value + holder2.sum;
    }

    static int getSubtreeSum(BinaryTree node, Holder holder) {
        if (node == null) {
            return 0;
        }

        var left = getSubtreeSum(node.left, holder);
        logger.info("[{}] [{}]", left, holder.last.value);
        var right = getSubtreeSum(node.right, holder);
        logger.info("[{}] [{}]", right, holder.last.value);
        var result = Math.max(left, right) + node.value;
        if (holder.sum < result) {
            holder.sum = result;
            holder.last = node;
        }
        return result;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    static class Holder {
        int sum = 0;
        BinaryTree last = new BinaryTree(-1);
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
