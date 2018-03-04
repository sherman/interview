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

import com.google.common.collect.ImmutableList;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IterativePostOrderTraversalTest {
    @Test
    public void traverse() {
        TreeNode node = new TreeNode(1);
        node.setLeft(new TreeNode(2));
        node.setRight(new TreeNode(3));

        node.getLeft().setLeft(new TreeNode(4));
        node.getLeft().setRight(new TreeNode(5));

        node.getRight().setRight(new TreeNode(6));

        assertEquals(IterativePostOrderTraversal.traverse(node), ImmutableList.of(4, 5, 2, 6, 3, 1));
    }
}
