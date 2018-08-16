package org.sherman.interview.tree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis M. Gabaydulin
 * @since 16.08.18
 */
public class MaxUniquePathTest {
    @Test
    public void getMaxUniquePath1() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(4, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(6, right);
        TreeNode right22 = new TreeNode(7, right);
        right.setLeft(left22);
        right.setRight(right22);

        TreeNode n1 = new TreeNode(8, right22);
        right22.setRight(n1);

        assertEquals(MaxUniquePath.getMaxUniquePath(root), 4);
    }

    @Test
    public void getMaxUniquePath2() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(2, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(2, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(1, right);
        TreeNode right22 = new TreeNode(2, right);
        right.setLeft(left22);
        right.setRight(right22);

        TreeNode n1 = new TreeNode(1, right22);
        right22.setRight(n1);

        assertEquals(MaxUniquePath.getMaxUniquePath(root), 2);
    }

    @Test
    public void getMaxUniquePath3() {
        TreeNode a4 = new TreeNode(4, null);
        TreeNode a5 = new TreeNode(5, a4);
        a4.setLeft(a5);
        TreeNode a6 = new TreeNode(4, a5);
        a5.setLeft(a6);
        TreeNode a7 = new TreeNode(4, a6);
        a6.setLeft(a7);

        TreeNode a8 = new TreeNode(6, a4);
        a4.setRight(a8);
        TreeNode a9 = new TreeNode(7, a8);
        a8.setLeft(a9);
        TreeNode a10 = new TreeNode(9, a8);
        a8.setRight(a10);
        TreeNode a11 = new TreeNode(10, a9);
        a9.setLeft(a11);


        assertEquals(MaxUniquePath.getMaxUniquePath(a4), 4);
    }
}
