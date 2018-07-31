package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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


        Map<Integer, Integer> expectedNodes = new HashMap<>();
        calculateExpectedNodes(treeNode, expectedNodes, 0);

        ValueHolder valueHolder = new ValueHolder();
        getMaxPerfectSubtreeLength(treeNode, valueHolder, expectedNodes);

        log.info("{}", valueHolder.max);
        return valueHolder.max;
    }

    private static int calculateExpectedNodes(TreeNode node, Map<Integer, Integer> expectedNodes, int level) {
        if (node == null) {
            return level - 1;
        } else {
            int leftLevel = calculateExpectedNodes(node.getLeft(), expectedNodes, level + 1);
            int rightLevel = calculateExpectedNodes(node.getRight(), expectedNodes,level + 1);

            int maxLevel = Math.max(leftLevel, rightLevel);
            int nodes = (int) (Math.pow(2, (maxLevel - level) + 1) - 1);

            log.info("{} {} {} {}", node.getId(), maxLevel, level, nodes);

            expectedNodes.put(node.getId(), nodes);

            return maxLevel;
        }
    }

    private static int getMaxPerfectSubtreeLength(TreeNode node, ValueHolder valueHolder, Map<Integer, Integer> expectedNodes) {
        if (node == null) {
            return 0;
        } else {
            int left = getMaxPerfectSubtreeLength(node.getLeft(), valueHolder, expectedNodes);
            int right = getMaxPerfectSubtreeLength(node.getRight(), valueHolder, expectedNodes);

            int nodes = 1 + left + right;

            log.info("{} {}", node.getId(), nodes);

            if (expectedNodes.get(node.getId()) == nodes) {
                valueHolder.max = Math.max(valueHolder.max, nodes);
            }

            return nodes;
        }
    }

    private static class ValueHolder {
        private int max;
    }
}
