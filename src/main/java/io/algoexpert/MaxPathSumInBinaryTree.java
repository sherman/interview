package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MaxPathSumInBinaryTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);

    public static int maxPathSum(BinaryTree tree) {
        var maxSumArray = findMaxSum(tree);
        return maxSumArray.get(1);
    }

    private static List<Integer> findMaxSum(BinaryTree node) {
        if (node == null) {
            return List.of(0, Integer.MIN_VALUE);
        }

        var leftMaxSumArray = findMaxSum(node.left);
        var leftMaxSumAsBranch = leftMaxSumArray.get(0);
        var leftMaxPathSum = leftMaxSumArray.get(1);

        var rightMaxSumArray = findMaxSum(node.right);
        var rightMaxSumAsBranch = rightMaxSumArray.get(0);
        var rightMaxPathSum = rightMaxSumArray.get(1);

        var maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
        var maxSumAsBranch = Math.max(maxChildSumAsBranch + node.value, node.value);
        var maxSumAsRootNode = Math.max(leftMaxSumAsBranch + node.value + rightMaxSumAsBranch, maxSumAsBranch);

        var maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode));

        logger.info("[{}] [{}] [{}]", node.value, maxSumAsBranch, maxPathSum);
        return List.of(maxSumAsBranch, maxPathSum);
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
