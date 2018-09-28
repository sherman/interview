package org.sherman.interview.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Denis M. Gabaydulin
 * @since 28.09.18
 */
public class FindNodeByIndex {
    private static final Logger log = LoggerFactory.getLogger(FindNodeByIndex.class);

    /**
     * Given a complete binary tree, check the node is exists in the tree, by index
     */
    public static boolean isNodeExists(TreeNode root, int index) {
        List<Integer> path = getPath(index);

        log.info("{}", path);

        return isNodeExists(root, path, 0);
    }

    private static boolean isNodeExists(TreeNode node, List<Integer> path, int index) {
        //log.info("{} {} {}", node.getId(), path.get(index), index);
        if (node == null) {
            return false;
        }

        if (index > path.size() - 1) {
            return false;
        }

        if (node.getId() != path.get(index)) {
            return false;
        }

        if (index == path.size() - 1) {
            return true;
        }

        if (path.get(index + 1) % 2 == 0) {
            return isNodeExists(node.getLeft(), path, index + 1);
        } else {
            return isNodeExists(node.getRight(), path, index + 1);
        }
    }

    private static List<Integer> getPath(int index) {
        List<Integer> path = new ArrayList<>();
        while (index != 0) {
            path.add(index);
            index = index % 2 == 0 ? index / 2 : (index - 1) / 2;
        }

        Collections.reverse(path);
        return path;
    }
}
