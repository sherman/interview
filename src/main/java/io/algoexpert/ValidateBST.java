package io.algoexpert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateBST {
    private static final Logger logger = LoggerFactory.getLogger(ValidateBST.class);

    public static boolean validateBst(BST tree) {
        if (tree == null) {
            return true;
        }

        return print(tree, tree.value, null);
    }

    private static boolean print(BST tree, int root, Boolean leftSubTree) {
        if (tree != null) {
            if (tree.left != null) {
                logger.info("l:[{}]", tree.left.value);
                if (tree.value <= tree.left.value) {
                    return false;
                }
            }

            if (tree.right != null) {
                logger.info("r:[{}]", tree.right.value);
                if (tree.value > tree.right.value) {
                    return false;
                }
            }

            if (leftSubTree == Boolean.TRUE && root <= tree.value) {
                return false;
            }
            if (leftSubTree == Boolean.FALSE && root > tree.value) {
                return false;
            }

            var left = print(tree.left, root, leftSubTree == null ? true : leftSubTree);
            var right = print(tree.right, root, leftSubTree == null ? false : leftSubTree);
            return left & right;
        } else {
            return true;
        }
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    @Test
    public void test1() {
        var root = new BST(10);
        var left = new BST(5);
        var right = new BST(15);
        root.left = left;
        root.right = right;
        var leftLeft = new BST(2);
        var leftRight = new BST(5);
        left.left = leftLeft;
        left.right = leftRight;
        var leftLeftLeft = new BST(1);
        leftLeft.left = leftLeftLeft;
        var rightLeft = new BST(13);
        var rightRight = new BST(22);
        right.left = rightLeft;
        right.right = rightRight;
        var rightLeftRight = new BST(14);
        rightLeft.right = rightLeftRight;

        Assert.assertTrue(validateBst(root));
    }

    @Test
    public void test2() {
        var root = new BST(10);
        var left = new BST(5);
        var right = new BST(15);
        root.left = left;
        root.right = right;
        var leftLeft = new BST(2);
        var leftRight = new BST(5);
        left.left = leftLeft;
        left.right = leftRight;
        var leftLeftLeft = new BST(1);
        leftLeft.left = leftLeftLeft;
        var leftRightRight = new BST(11);
        leftRight.right = leftRightRight;
        var rightRight = new BST(22);
        right.right = rightRight;
        Assert.assertFalse(validateBst(root));
    }
}
