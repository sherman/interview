package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis M. Gabaydulin
 * @since 22.06.18
 */
public class IsTreeBalanced {
    private static final Logger log = LoggerFactory.getLogger(IsTreeBalanced.class);

    public static boolean isTreeBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        int left = getHeight(node.getLeft());
        int right = getHeight(node.getRight());

        log.info("{}", Math.abs(left - right));

        return Math.abs(left - right) <= 1;
    }

    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 1;
        }

        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }
}
