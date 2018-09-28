package org.sherman.interview.tree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Denis M. Gabaydulin
 * @since 28.09.18
 */
public class FindNodeByIndexTest {
    @Test
    public void isNodeExists() {
        TreeNode root = new TreeNode(1, null);
        TreeNode n2 = new TreeNode(2, root);
        TreeNode n3 = new TreeNode(3, root);
        root.setLeft(n2);
        root.setRight(n3);

        TreeNode n4 = new TreeNode(4, n2);
        TreeNode n5 = new TreeNode(5, n2);
        TreeNode n6 = new TreeNode(6, n3);
        TreeNode n7 = new TreeNode(7, n3);

        n2.setLeft(n4);
        n2.setRight(n5);

        n3.setLeft(n6);
        n3.setRight(n7);

        TreeNode n8 = new TreeNode(8, n4);
        TreeNode n9 = new TreeNode(9, n4);

        n4.setLeft(n8);
        n4.setRight(n9);

        assertTrue(FindNodeByIndex.isNodeExists(root, 4));
        assertTrue(FindNodeByIndex.isNodeExists(root, 1));
        assertFalse(FindNodeByIndex.isNodeExists(root, 10));
        assertTrue(FindNodeByIndex.isNodeExists(root, 8));
        assertTrue(FindNodeByIndex.isNodeExists(root, 9));
    }
}
