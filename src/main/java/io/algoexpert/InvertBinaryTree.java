package io.algoexpert;

public class InvertBinaryTree {
    public static void invertBinaryTree(BinaryTree tree) {
        invertHelper(tree);
    }

    private static void invertHelper(BinaryTree node) {
        if (node != null) {
            var tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            invertHelper(node.left);
            invertHelper(node.right);
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
