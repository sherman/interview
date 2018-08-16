package org.sherman.interview.tree;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis M. Gabaydulin
 * @since 16.08.18
 */
public class MaxUniquePath {
    private static final Logger log = LoggerFactory.getLogger(MaxUniquePath.class);

    public static int getMaxUniquePath(@NotNull TreeNode treeNode) {
        Set<Integer> values = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        findMaxUniquePath(treeNode, values, result);
        result.add(treeNode.getId());
        return result.size();
    }

    private static void findMaxUniquePath(TreeNode node, Set<Integer> values, Set<Integer> result) {
        if (node == null) {
            if (result.size() < values.size()) {
                result.clear();
                result.addAll(values);
            }
        } else {
            values.add(node.getId());
            findMaxUniquePath(node.getLeft(), values, result);
            findMaxUniquePath(node.getRight(), values, result);
            values.remove(node.getId());
        }
    }
}
