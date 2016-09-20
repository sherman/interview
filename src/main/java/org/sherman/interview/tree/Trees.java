package org.sherman.interview.tree;

/*
 * Copyright (C) 2016 by Denis M. Gabaydulin
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trees {
    private static final Logger log = LoggerFactory.getLogger(Trees.class);

    public static void traverse(TreeNode node, String path) {
        path = path + "," + node.getId();
        if (node.getParent() != null) {
            log.info("{}", path);
        }

        if (node.hasChildren()) {
            if (node.getLeft() != null) {
                traverse(node.getLeft(), path);
            }

            if (node.getRight() != null) {
                traverse(node.getRight(), path);
            }
        }
    }
}
