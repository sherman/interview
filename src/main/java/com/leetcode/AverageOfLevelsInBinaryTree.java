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

import com.leetcode.LongestUnivaluePath.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AverageOfLevelsInBinaryTree {
    private static final Logger log = LoggerFactory.getLogger(AverageOfLevelsInBinaryTree.class);

    public static List<Double> getAverageLevel(TreeNode node) {
        SortedMap<Integer, LevelInfo> levelInfo = new TreeMap<>();

        traverseTree(node, levelInfo, 0);

        List<Double> levelsAvg = new ArrayList<>();
        for (int level : levelInfo.keySet()) {
            levelsAvg.add(levelInfo.get(level).getAverage());
        }
        return levelsAvg;
    }

    private static void traverseTree(TreeNode node, SortedMap<Integer, LevelInfo> levelInfo, int level) {
        if (node == null) {
            return;
        }

        LevelInfo info;
        LevelInfo current = levelInfo.putIfAbsent(level, info = new LevelInfo());
        if (current == null) {
            current = info;
        }

        current.addElement(node.val);

        traverseTree(node.left, levelInfo, level + 1);
        traverseTree(node.right, levelInfo, level + 1);
    }

    private static class LevelInfo {
        long sum;
        int count;

        void addElement(int value) {
            sum += value;
            count++;
        }

        Double getAverage() {
            return (double) sum / count;
        }

    }
}
