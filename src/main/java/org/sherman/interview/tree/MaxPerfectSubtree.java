package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Denis M. Gabaydulin
 * @since 30.07.18
 */
public class MaxPerfectSubtree {
    private static final Logger log = LoggerFactory.getLogger(MaxPerfectSubtree.class);

    public static int getMaxPerfectSubtreeLength(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        ValueHolder valueHolder = new ValueHolder();

        getMaxPerfectSubtreeLength_(treeNode, valueHolder);
        log.info("{}", valueHolder.max);
        return valueHolder.max;
    }

    private static int getMaxPerfectSubtreeLength_(TreeNode node, ValueHolder valueHolder) {
        if (node == null) {
            return 0;
        } else {
            int left = getMaxPerfectSubtreeLength_(node.getLeft(), valueHolder);
            int right = getMaxPerfectSubtreeLength_(node.getRight(), valueHolder);
            log.info("{} {} {}", node, left, right);
            valueHolder.max = Math.max(valueHolder.max, Math.min(left, right) * 2 + 1);
            return 1 + left + right;
        }
    }

    private static class ValueHolder {
        private int max;
    }
}
