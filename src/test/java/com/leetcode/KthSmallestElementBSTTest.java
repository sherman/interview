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

public class KthSmallestElementBSTTest {
    @Test
    public void case1() {
        LongestUnivaluePath.TreeNode node = new LongestUnivaluePath.TreeNode(5);
        node.left = new LongestUnivaluePath.TreeNode(2);
        node.right = new LongestUnivaluePath.TreeNode(21);

        node.left.left = new LongestUnivaluePath.TreeNode(1);
        node.left.right = new LongestUnivaluePath.TreeNode(3);

        node.right.left = new LongestUnivaluePath.TreeNode(19);
        node.right.right = new LongestUnivaluePath.TreeNode(25);

        assertEquals(KthSmallestElementBST.kthSmallest(node, 2), 2);
    }

    @Test
    public void case2() {
        LongestUnivaluePath.TreeNode node = new LongestUnivaluePath.TreeNode(1);

        assertEquals(KthSmallestElementBST.kthSmallest(node, 1), 1);
    }

    @Test
    public void case3() {
        LongestUnivaluePath.TreeNode node = new LongestUnivaluePath.TreeNode(1);
        node.right = new LongestUnivaluePath.TreeNode(2);

        assertEquals(KthSmallestElementBST.kthSmallest(node, 2), 2);
    }

    @Test
    public void case4() {
        LongestUnivaluePath.TreeNode node = new LongestUnivaluePath.TreeNode(3);
        node.left = new LongestUnivaluePath.TreeNode(1);
        node.right = new LongestUnivaluePath.TreeNode(4);
        node.left.right = new LongestUnivaluePath.TreeNode(2);

        assertEquals(KthSmallestElementBST.kthSmallest(node, 2), 2);
    }
}
