package org.sherman.interview.tree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Denis M. Gabaydulin
 * @since 05.03.18
 */
public class IsTreeBSTTest {
    @Test
    public void bst() {
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

        assertTrue(IsTreeBST.IsTreeBST(root));
    }

    @Test
    public void notBst1() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        TreeNode right = new TreeNode(11, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(10, right);
        TreeNode right22 = new TreeNode(13, right);
        right.setLeft(left22);
        right.setRight(right22);

        assertFalse(IsTreeBST.IsTreeBST(root));
    }

    @Test
    public void notBst2() {
        TreeNode root = new TreeNode(10, null);
        TreeNode left = new TreeNode(3, root);
        TreeNode right = new TreeNode(12, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(2, left);
        TreeNode right11 = new TreeNode(11, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(11, right);
        TreeNode right22 = new TreeNode(13, right);
        right.setLeft(left22);
        right.setRight(right22);

        assertFalse(IsTreeBST.IsTreeBST(root));
    }
}
