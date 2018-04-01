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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LCSTest {
    @Test
    public void case1() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        node.right.right = new TreeNode(6);

        LCS solution = new LCS();
        assertEquals(solution.lowestCommonAncestor(node, node.left.left, node.left.right).val, 2);
        assertEquals(solution.lowestCommonAncestor(node, node.left, node.left.right).val, 2);
    }

    @Test
    public void case2() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);

        node.left.left = new TreeNode(4);
        node.left.left.left = new TreeNode(5);

        LCS solution = new LCS();
        assertEquals(solution.lowestCommonAncestor(node, node.left.left, node.left.left.left).val, 4);
    }

    @Test
    public void case3() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);

        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        node.left.left.left = new TreeNode(6);
        node.left.left.right = new TreeNode(7);
        node.left.right.left = new TreeNode(8);
        node.left.right.right = new TreeNode(9);

        LCS solution = new LCS();
        assertEquals(solution.lowestCommonAncestor(node, node.left.left.right, node.left.right.left).val, 2);
    }

    @Test
    public void case4() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);

        LCS solution = new LCS();
        assertEquals(solution.lowestCommonAncestor(node, node, node.left).val, 1);
    }
}
