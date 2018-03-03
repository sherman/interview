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

import com.leetcode.LongestUnivaluePath.TreeNode;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> nodes = new Stack<>();

    public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            nodes.add(cur);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                return;
            }
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = nodes.pop();
        TreeNode cur = node;
        // traversal right branch
        if (cur.right != null) {
            cur = cur.right;
            while (true) {
                nodes.add(cur);
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    break;
                }
            }
        }
        return node.val;
    }
}
