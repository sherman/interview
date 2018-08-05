package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        traverseNode(treeNode, valueHolder);

        return valueHolder.max;
    }

    private static int getPerfectSubTreeLength(Map<Integer, Integer> expectedNodes) {
        Optional<Integer> brokenLevel = expectedNodes.keySet().stream()
            .sorted()
            .filter(level -> expectedNodes.get(level) < Math.pow(2, level))
            .findFirst();

        int maxLevel = 0;
        if (brokenLevel.isPresent()) {
            maxLevel = brokenLevel.get();
        } else {
            maxLevel = expectedNodes.keySet().stream()
                .max(Comparator.comparingInt(v -> v))
                .get() + 1;
        }

        int sum = 0;
        for (int level = 0; level < maxLevel; level++) {
            sum += Math.pow(2, level);
        }
        return sum;
    }

    private static void calculateNodesPerLevel(TreeNode node, Map<Integer, Integer> expectedNodes, int level) {
        if (node != null) {
            Integer nodes = expectedNodes.get(level);
            expectedNodes.put(level, nodes == null ? 1 : nodes + 1);

            calculateNodesPerLevel(node.getLeft(), expectedNodes, level + 1);
            calculateNodesPerLevel(node.getRight(), expectedNodes, level + 1);
        }
    }

    private static void traverseNode(TreeNode node, ValueHolder valueHolder) {
        if (node != null) {
            Map<Integer, Integer> nodesPerLevel = new HashMap<>();
            calculateNodesPerLevel(node, nodesPerLevel, 0);
            valueHolder.max = Math.max(valueHolder.max, getPerfectSubTreeLength(nodesPerLevel));

            traverseNode(node.getLeft(), valueHolder);
            traverseNode(node.getRight(), valueHolder);
        }
    }

    private static class ValueHolder {
        private int max;
    }
}
