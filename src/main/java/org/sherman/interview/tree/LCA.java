package org.sherman.interview.tree;

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

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LCA {
    private static final Logger log = LoggerFactory.getLogger(LCA.class);

    public static TreeNode getLCA(@NotNull TreeNode root, @NotNull TreeNode a, @NotNull TreeNode b) {
        Stack<TreeNode> aPath = new Stack<>();
        Stack<TreeNode> bPath = new Stack<>();
        collectPath(root, a, aPath);
        aPath.push(root);
        collectPath(root, b, bPath);
        bPath.push(root);
        log.info("{}", aPath);
        log.info("{}", bPath);

        TreeNode res = null;
        while (!aPath.isEmpty() && !bPath.isEmpty()) {
            TreeNode a1 = aPath.pop();
            TreeNode b1 = bPath.pop();
            if (a1.equals(b1)) {
                res = a1;
            } else {
                break;
            }
        }

        return res;
    }

    public static boolean collectPath(TreeNode node, TreeNode target, Stack<TreeNode> path) {
        if (node == null) {
            return false;
        }

        if (node.equals(target)) {
            return true;
        }

        if (collectPath(node.getLeft(), target, path)) {
            path.push(node);
            return true;
        }

        if (collectPath(node.getRight(), target, path)) {
            path.push(node);
            return true;
        }

        return false;
    }
}
