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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LongestUnivaluePathTest {
    private static final Logger log = LoggerFactory.getLogger(LongestUnivaluePathTest.class);

    @Test
    public void case1() {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(5);

        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(1);

        node.right.right = new TreeNode(5);

        assertEquals(LongestUnivaluePath.longestUnivaluePath(node), 2);
    }

    @Test
    public void case2() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(4);
        node.right = new TreeNode(5);

        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(4);

        node.right.left = new TreeNode(5);

        assertEquals(LongestUnivaluePath.longestUnivaluePath(node), 2);
    }

    @Test
    public void case3() {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(-7);
        node.right = new TreeNode(-3);

        node.right.left = new TreeNode(-9);
        node.right.right = new TreeNode(-3);

        node.right.right.left = new TreeNode(-4);

        node.right.left.left = new TreeNode(9);
        node.right.left.right = new TreeNode(-7);

        node.right.left.left.left = new TreeNode(6);

        node.right.left.left.left.left = new TreeNode(0);
        node.right.left.left.left.right = new TreeNode(6);

        node.right.left.left.left.left.right = new TreeNode(-1);

        node.right.left.left.left.right.left = new TreeNode(-4);

        node.right.left.right.left = new TreeNode(-6);
        node.right.left.right.right = new TreeNode(-6);

        node.right.left.right.left.left = new TreeNode(5);

        node.right.left.right.right.left = new TreeNode(9);
        node.right.left.right.right.left.left = new TreeNode(-2);

        assertEquals(LongestUnivaluePath.longestUnivaluePath(node), 1);
    }
}
