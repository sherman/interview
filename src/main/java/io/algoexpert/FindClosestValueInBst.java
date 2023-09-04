package io.algoexpert;

public class FindClosestValueInBst {
    public static int findClosestValueInBst(BST tree, int target) {
        var holder = new ValueHolder();
        holder.value = Integer.MAX_VALUE;
        holder.diff = Integer.MAX_VALUE;
        findClosestValueInBst(tree, target, holder);
        return holder.value;
    }

    private static void findClosestValueInBst(BST tree, int target, ValueHolder holder) {
        if (Math.abs(tree.value - target) < holder.diff) {
            holder.value = tree.value;
            holder.diff = Math.abs(tree.value - target);
        }

        if (target < tree.value && tree.left != null) {
            findClosestValueInBst(tree.left, target, holder);
        }

        if (target >= tree.value && tree.right != null) {
            findClosestValueInBst(tree.right, target, holder);
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

    private static class ValueHolder {
        int value;
        int diff;
    }
}
