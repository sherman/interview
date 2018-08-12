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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickUnion extends BaseQuickFind implements UnionFind {
    private static final Logger log = LoggerFactory.getLogger(QuickUnion.class);

    public QuickUnion(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {
        checkValidId(p);
        checkValidId(q);

        log.info("{}", ids);

        int i = getRoot(p);
        int j = getRoot(q);
        ids[i] = j;

        log.info("{}", ids);
    }

    @Override
    public boolean isConnected(int p, int q) {
        checkValidId(p);
        checkValidId(q);

        return getRoot(p) == getRoot(q);
    }

    private int getRoot(int id) {
        while (ids[id] != id) {
            id = ids[id];
        }
        return id;
    }
}
