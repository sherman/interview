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

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LCATest {
    @Test
    public void withStack() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(4, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(6, right);
        TreeNode right22 = new TreeNode(7, right);
        right.setLeft(left22);
        right.setRight(right22);

        TreeNode n1 = new TreeNode(8, right22);
        right22.setRight(n1);

        assertEquals(LCA.getLCA(root, left11, right22), root);
        assertEquals(LCA.getLCA(root, left22, right22), right);
        assertEquals(LCA.getLCA(root, left22, n1), right);
        assertEquals(LCA.getLCA(root, left, left11), root);
        assertEquals(LCA.getLCA(root, n1, right), root);
    }

    @Test
    public void withParent() {
        TreeNode root = new TreeNode(1, null);
        TreeNode left = new TreeNode(2, root);
        TreeNode right = new TreeNode(3, root);
        root.setLeft(left);
        root.setRight(right);

        TreeNode left11 = new TreeNode(4, left);
        TreeNode right11 = new TreeNode(5, left);
        left.setLeft(left11);
        left.setRight(right11);

        TreeNode left22 = new TreeNode(6, right);
        TreeNode right22 = new TreeNode(7, right);
        right.setLeft(left22);
        right.setRight(right22);

        TreeNode n1 = new TreeNode(8, right22);
        right22.setRight(n1);

        assertEquals(LCA.getLCA(left11, right22), root);
        assertEquals(LCA.getLCA(left22, right22), right);
        assertEquals(LCA.getLCA(left22, n1), right);
        assertEquals(LCA.getLCA(left, left11), root);
        assertEquals(LCA.getLCA(n1, right), root);
    }
}
