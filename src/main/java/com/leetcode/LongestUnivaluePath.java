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

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongestUnivaluePath {
    private static final Logger log = LoggerFactory.getLogger(LongestUnivaluePath.class);

    public static int longestUnivaluePath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int max = longestUnivaluePath(node.left, node.val)
            + longestUnivaluePath(node.right, node.val);

        int maxLeft = longestUnivaluePath(node.left);
        int maxRight = longestUnivaluePath(node.right);

        return Math.max(Math.max(max, maxLeft), maxRight);
    }

    private static int longestUnivaluePath(TreeNode node, int value) {
        if (node == null || node.val != value) {
            return 0;
        }

        return Math.max(longestUnivaluePath(node.left, value), longestUnivaluePath(node.right, value)) + 1;
    }

    public static class TreeNode {
        int val;

        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("val", val)
                .toString();
        }
    }
}
