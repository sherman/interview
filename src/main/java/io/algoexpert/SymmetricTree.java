package io.algoexpert;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SymmetricTree {
    private static final Logger logger = LoggerFactory.getLogger(SymmetricTree.class);

    public boolean symmetricalTree(BinaryTree tree) {
        return isMirror(tree, tree);
    }

    private boolean isMirror(BinaryTree left, BinaryTree right) {
        if (left == null && right == null) {
            return true;
        }

        logger.info("[{}] [{}]", left, right);

        if (left != null && right != null && left.value == right.value) {
            return isMirror(left.left, right.right) && isMirror(right.right, left.left);
        } else {
            return false;
        }
    }

    @Test
    public void test() {
        var node = new BinaryTree(1);
        Assert.assertTrue(symmetricalTree(node));

        node = new BinaryTree(1);
        node.left = new BinaryTree(2);
        node.right = new BinaryTree(2);
        Assert.assertTrue(symmetricalTree(node));

        node = new BinaryTree(1);
        node.left = new BinaryTree(2);
        node.right = new BinaryTree(2);
        node.left.left = new BinaryTree(3);
        node.left.right = new BinaryTree(4);
        node.right.right = new BinaryTree(3);
        node.right.left = new BinaryTree(4);
        node.left.left.left = new BinaryTree(5);
        node.left.right.right = new BinaryTree(6);
        node.right.right.right = new BinaryTree(5);
        node.right.left.left = new BinaryTree(6);
        Assert.assertTrue(symmetricalTree(node));
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
        }
    }
}
