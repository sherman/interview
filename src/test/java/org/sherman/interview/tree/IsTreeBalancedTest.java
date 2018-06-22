package org.sherman.interview.tree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Denis M. Gabaydulin
 * @since 22.06.18
 */
public class IsTreeBalancedTest {
    @Test
    public void balanced() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        TreeNode right = new TreeNode(12, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(11, right);
        TreeNode right22 = new TreeNode(13, right);
        right.setLeft(left22);
        right.setRight(right22);

        assertTrue(IsTreeBalanced.isTreeBalanced(root));
    }

    @Test
    public void notBalanced() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        TreeNode right = new TreeNode(12, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        left11.setLeft(new TreeNode(42, left11));

        assertFalse(IsTreeBalanced.isTreeBalanced(root));
    }

    @Test
    public void balanced2() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        TreeNode right = new TreeNode(12, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        assertFalse(IsTreeBalanced.isTreeBalanced(root));
    }

    @Test
    public void notBalanced2() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        root.setLeft(left);

        TreeNode left11 = new TreeNode(2, left);
        left.setLeft(left11);

        assertTrue(IsTreeBalanced.isTreeBalanced(root));
    }
}
