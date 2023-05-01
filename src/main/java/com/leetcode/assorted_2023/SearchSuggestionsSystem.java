package com.leetcode.assorted_2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class SearchSuggestionsSystem {
    private static final Logger logger = LoggerFactory.getLogger(SearchSuggestionsSystem.class);

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        var trie = new TrieNode();

        for (var product : products) {
            trie.addWord(product);
        }

        var result = new ArrayList<List<String>>();

        for (var i = 1; i <= searchWord.length(); i++) {
            var prefix = searchWord.substring(0, i);
            var byPrefix = trie.getWordsByPrefix(prefix);
            Collections.sort(byPrefix, String.CASE_INSENSITIVE_ORDER);
            if (byPrefix.size() > 3) {
                byPrefix = byPrefix.subList(0, 3);
            }
            result.add(byPrefix);
        }

        return result;
    }

    @Test
    public void trieTest() {
        TrieNode node = new TrieNode();
        node.addWord("flow");
        node.addWord("frog");
        logger.info("[{}]", node.getWordsByPrefix("f"));
    }

    @Test
    public void test() {
        var result = suggestedProducts(new String[] {"havana"}, "tatiana");
        for (var subResult : result) {
            logger.info("[{}]", subResult);
        }

        /*var result = suggestedProducts(new String[] {"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse");
        for (var subResult : result) {
            logger.info("[{}]", subResult);
        }*/
    }

    private static class TrieNode {
        private boolean terminal;
        private Map<Character, TrieNode> children = new HashMap<>();

        private String word;

        public void addWord(String word) {
            var current = this;
            for (var ch : word.toCharArray()) {
                var node = current.children.get(ch);
                if (node == null) {
                    current.children.put(ch, new TrieNode());
                    node = current.children.get(ch);
                }
                current = node;
            }
            current.terminal = true;
            current.word = word;
        }

        public List<String> getWordsByPrefix(String prefix) {
            var result = new ArrayList<String>();

            var matched = 0;

            // find nodes by prefix
            var current = this;
            for (var ch : prefix.toCharArray()) {
                var node = current.children.get(ch);
                if (node != null) {
                    current = node;
                    matched++;
                }
            }

            if (matched == 0) {
                return result;
            }

            var queue = new LinkedList<TrieNode>();
            queue.add(current);
            while (!queue.isEmpty()) {
                var node = queue.poll();
                if (node.word != null) {
                    result.add(node.word);
                }
                queue.addAll(node.children.values());
            }

            return result;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                "terminal=" + terminal +
                ", children=" + children +
                '}';
        }
    }
}
