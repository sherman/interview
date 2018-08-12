package org.sherman.interview.unionfind;

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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UnionFindTest {
    @Test
    public void quickFind() {
        UnionFind unionFind = new QuickFind(10);
        assertFalse(unionFind.isConnected(1, 2));
        unionFind.union(1, 2);
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(5, 7);
        assertTrue(unionFind.isConnected(5, 7));
        unionFind.union(7, 8);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(7, 1);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        assertTrue(unionFind.isConnected(1, 7));
        assertTrue(unionFind.isConnected(7, 1));
    }

    @Test
    public void quickUnion() {
        UnionFind unionFind = new QuickUnion(10);
        assertFalse(unionFind.isConnected(1, 2));
        unionFind.union(1, 2);
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(5, 7);
        assertTrue(unionFind.isConnected(5, 7));
        unionFind.union(7, 8);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(7, 1);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        assertTrue(unionFind.isConnected(1, 7));
        assertTrue(unionFind.isConnected(7, 1));
    }

    @Test
    public void weightedUnionFind() {
        UnionFind unionFind = new WeightedUnionFind(10);
        assertFalse(unionFind.isConnected(1, 2));
        unionFind.union(1, 2);
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(5, 7);
        assertTrue(unionFind.isConnected(5, 7));
        unionFind.union(7, 8);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        unionFind.union(7, 1);
        assertTrue(unionFind.isConnected(5, 7));
        assertTrue(unionFind.isConnected(7, 8));
        assertTrue(unionFind.isConnected(1, 2));
        assertTrue(unionFind.isConnected(1, 7));
        assertTrue(unionFind.isConnected(7, 1));
    }
}
