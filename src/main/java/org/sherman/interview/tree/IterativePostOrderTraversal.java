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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePostOrderTraversal {
    private static final Logger log = LoggerFactory.getLogger(IterativePostOrderTraversal.class);

    public static List<Integer> traverse(@NotNull TreeNode node) {
        Stack<TreeNode> one = new Stack<>();
        Stack<TreeNode> two = new Stack<>();

        one.push(node);
        while (!one.isEmpty()) {
            TreeNode top = one.pop();
            two.push(top);

            TreeNode left = top.getLeft();
            if (left != null) {
                one.push(left);
            }

            TreeNode right = top.getRight();
            if (right != null) {
                one.push(right);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!two.isEmpty()) {
            int id = two.pop().getId();
            result.add(id);
            log.info("{}", id);
        }

        return result;
    }
}
