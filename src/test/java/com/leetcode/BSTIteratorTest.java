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

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class BSTIteratorTest {
    private static final Logger log = LoggerFactory.getLogger(BSTIteratorTest.class);

    @Test
    public void case1() {
        TreeNode node = new TreeNode(25);
        node.left = new TreeNode(20);
        node.right = new TreeNode(36);

        node.left.left = new TreeNode(10);
        node.left.right = new TreeNode(22);

        node.right.left = new TreeNode(30);
        node.right.right = new TreeNode(40);

        List<Integer> result = new ArrayList<>();
        BSTIterator iterator = new BSTIterator(node);
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertEquals(result, ImmutableList.of(10, 20, 22, 25, 30, 36, 40));
    }
}
