package org.sherman.interview.trie;

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
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private static final Logger log = LoggerFactory.getLogger(Trie.class);

    private TrieNode root = new TrieNode();

    public void insert(@NotNull String key) {
        char[] chars = key.toCharArray();
        TrieNode current = root;

        for (int i = 0; i < key.length(); i++) {
            if (current.hasChildren(chars[i])) {
                current = current.children.get(chars[i]);
            } else {
                current.children.put(chars[i], new TrieNode());
                current = current.children.get(chars[i]);
            }

            if (i == key.length() - 1) {
                current.terminal = true;
            }
        }
    }

    public boolean search(@NotNull String key) {
        char[] chars = key.toCharArray();
                TrieNode current = root;

        for (int i = 0; i < key.length(); i++) {
            if (current.children.containsKey(chars[i])) {
                current = current.children.get(chars[i]);
            } else {
                return false;
            }
        }

        return current.terminal;
    }

    public void print() {
        log.info("{}", root);
    }

    private static class TrieNode {
        private boolean terminal;
        private Map<Character, TrieNode> children = new HashMap<>();

        boolean hasChildren(char chr) {
            return children.containsKey(chr);
        }

        @Test
        public String toString() {
            return children.toString() + ":" + terminal;
        }
    }
}
