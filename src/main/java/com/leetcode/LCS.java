package com.leetcode;

/*
 * Copyright (C) 2018 by Denis M. Gabaydulin
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LCS {
    private static final Logger log = LoggerFactory.getLogger(LCS.class);

    private TreeNode node;
    private boolean found = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> path1 = getPath(root, p);
        Stack<TreeNode> path2 = getPath(root, q);

        TreeNode prev = null;
        while (!path1.isEmpty() && !path2.isEmpty()) {
            TreeNode node1 = path1.pop();
            TreeNode node2 = path2.pop();

            if (node1.equals(node2)) {
                prev = node1;
            } else {
                break;
            }
        }

        return prev;
    }

    private Stack<TreeNode> getPath(TreeNode root, TreeNode need) {
        if (root == null) {
            return null;
        }

        if (root == need) {
            Stack<TreeNode> path = new Stack<>();
            path.push(root);
            return path;
        }

        Stack<TreeNode> left = getPath(root.left, need);
        Stack<TreeNode> right = getPath(root.right, need);

        if (left != null) {
            left.push(root);
            return left;
        }

        if (right != null) {
            right.push(root);
            return right;
        }

        return null;
    }
}
