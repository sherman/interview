package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis M. Gabaydulin
 * @since 05.03.18
 */
public class IsTreeBST {
    public static final Logger log = LoggerFactory.getLogger(IsTreeBST.class);

    public static boolean IsTreeBST(TreeNode node) {
        if (node == null) {
            return true;
        }

        return isSuBTreeBST(node, 0, Integer.MAX_VALUE);

    }

    public static boolean isSuBTreeBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getId() < min || node.getId() > max) {
            log.info("Violation: {} is not between {} and {}", node.getId(), min, max);
            return false;
        }

        boolean left = isSuBTreeBST(node.getLeft(), min, node.getId() - 1);
        boolean right = isSuBTreeBST(node.getRight(), node.getId() + 1, max);

        return left && right;
    }
}
