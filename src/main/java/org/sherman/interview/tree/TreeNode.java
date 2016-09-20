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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static java.util.Optional.ofNullable;

public class TreeNode {
    private final int id;
    private final TreeNode parent;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int id, TreeNode parent, TreeNode left, TreeNode right) {
        this(id, parent);
        this.left = left;
        this.right = right;
    }

    public TreeNode(int id, TreeNode parent) {
        this.id = id;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public boolean hasChildren() {
        return left != null || right != null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("parent", ofNullable(parent).map(TreeNode::getId).map(java.util.Objects::toString).orElse(""))
                .add("left", ofNullable(left).map(TreeNode::getId).map(java.util.Objects::toString).orElse(""))
                .add("right", ofNullable(right).map(TreeNode::getId).map(java.util.Objects::toString).orElse(""))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode that = (TreeNode) o;

        return Objects.equal(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
