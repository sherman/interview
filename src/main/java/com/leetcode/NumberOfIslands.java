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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands {
    private static final Logger log = LoggerFactory.getLogger(NumberOfIslands.class);

    public static int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int n = grid.length * grid[0].length;
        WeightedUnionFind unionFind = new WeightedUnionFind(n);

        boolean[] isEarth = new boolean[n];

        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        if (!unionFind.isConnected(counter, counter - 1)) {
                            unionFind.union(counter, counter - 1);
                        }
                    }

                    if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
                        if (!unionFind.isConnected(counter, counter + 1)) {
                            unionFind.union(counter, counter + 1);
                        }
                    }

                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        if (!unionFind.isConnected(counter, counter - grid[0].length)) {
                            unionFind.union(counter, counter - grid[0].length);
                        }
                    }

                    if (i + 1 < grid.length && grid[i + 1][j] == '1') {
                        if (!unionFind.isConnected(counter, counter + grid[0].length)) {
                            unionFind.union(counter, counter + grid[0].length);
                        }
                    }
                    isEarth[counter] = true;
                }
                counter++;
            }
        }

        int result = 0;
        for (int root : unionFind.getConnectedComponents()) {
            if (isEarth[root]) {
                result++;
            }
        }
        log.info("{}", isEarth);
        log.info("{}", result);
        log.info("{}", unionFind.ids);

        return result;
    }

    public static class WeightedUnionFind {
        private final int[] weight;
        final int[] ids;

        public WeightedUnionFind(int n) {
            this.ids = new int[n];

            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }

            weight = new int[n];
            for (int i = 0; i < weight.length; i++) {
                weight[i] = 1;
            }
        }

        public void union(int p, int q) {
            int i = getRoot(p);
            int j = getRoot(q);

            //log.info("{} {}", ids, weight);

            if (weight[i] >= weight[j]) {
                ids[j] = i;
                weight[i] = weight[i] + weight[j];
            } else {
                ids[i] = j;
                weight[j] = weight[j] + weight[i];
            }
        }

        public boolean isConnected(int p, int q) {
            return getRoot(p) == getRoot(q);
        }

        public List<Integer> getConnectedComponents() {
            List<Integer> roots = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == i) {
                    roots.add(i);
                }
            }
            return roots;
        }

        private int getRoot(int id) {
            while (ids[id] != id) {
                id = ids[id];
            }
            return id;
        }
    }
}
