package org.sherman.interview.tree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 30.07.18
 */
public class MaxPerfectSubtreeTest {
    @Test
    public void getMaxPerfectSubtreeLength1() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode right4 = new TreeNode(4, left);
        left.setRight(right4);

        TreeNode left5 = new TreeNode(5, right);
        TreeNode right6 = new TreeNode(6, right);
        right.setLeft(left5);
        right.setRight(right6);

        TreeNode left7 = new TreeNode(7, left5);
        TreeNode right8 = new TreeNode(8, left5);
        left5.setLeft(left7);
        left5.setRight(right8);

        TreeNode left9 = new TreeNode(9, right6);
        TreeNode right10 = new TreeNode(10, right6);
        right6.setLeft(left9);
        right6.setRight(right10);

        TreeNode left11 = new TreeNode(11, right10);
        right10.setLeft(left11);

        assertEquals(MaxPerfectSubtree.getMaxPerfectSubtreeLength(root), 7);
    }

    @Test
    public void getMaxPerfectSubtreeLength2() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);


        assertEquals(MaxPerfectSubtree.getMaxPerfectSubtreeLength(root), 3);
    }

    @Test
    public void getMaxPerfectSubtreeLength3() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode right4 = new TreeNode(4, left);
        left.setRight(right4);

        assertEquals(MaxPerfectSubtree.getMaxPerfectSubtreeLength(root), 3);
    }

    @Test
    public void getMaxPerfectSubtreeLength4() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode right4 = new TreeNode(4, left);
        left.setRight(right4);

        TreeNode left5 = new TreeNode(5, right);

        TreeNode left7 = new TreeNode(7, left5);
        TreeNode right8 = new TreeNode(8, left5);
        left5.setLeft(left7);
        left5.setRight(right8);


        assertEquals(MaxPerfectSubtree.getMaxPerfectSubtreeLength(root), 3);
    }
}
