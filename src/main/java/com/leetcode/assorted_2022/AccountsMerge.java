package com.leetcode.assorted_2022;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountsMerge {
    private static final Logger logger = LoggerFactory.getLogger(AccountsMerge.class);

    /**
     * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
     * <p>
     * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
     * <p>
     * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        var emailsToAccounts = new HashMap<String, String>();
        var graph = new UndirectedGraph();
        for (var acc : accounts) {
            Preconditions.checkArgument(acc.size() > 1);
            if (acc.size() == 2) {
                graph.add(new Vertex(acc.get(1)));
                emailsToAccounts.put(acc.get(1), acc.get(0));
            } else {
                for (int i = 1; i < acc.size(); i++) {
                    for (int j = 1; j < acc.size(); j++) {
                        if (!acc.get(i).equals(acc.get(j))) {
                            graph.add(new Vertex(acc.get(i)), new Vertex(acc.get(j)));
                        }
                    }
                    emailsToAccounts.put(acc.get(i), acc.get(0));
                }
            }
        }

        var result = new ArrayList<List<String>>();
        var global = new HashSet<Vertex>();
        for (var v : graph.getAllVertices()) {
            var visited = new TreeSet<>(Comparator.comparing(Vertex::email));
            if (!global.contains(v)) {
                dfs(graph, visited, global, v);
            }
            logger.info("[{}] => [{}]", v, visited);

            if (!visited.isEmpty()) {
                var acc = new ArrayList<String>();
                acc.add(emailsToAccounts.get(v.email()));
                acc.addAll(visited.stream().map(Vertex::email).toList());
                result.add(acc);
            }
        }

        return result;
    }

    public static void dfs(UndirectedGraph graph, TreeSet<Vertex> visited, Set<Vertex> global, Vertex start) {
        var stack = new Stack<Vertex>();
        stack.push(start);

        while (!stack.isEmpty()) {
            var v = stack.pop();
            global.add(v);
            visited.add(v);
            for (var neighbour : graph.getListOfNeighbours(v)) {
                if (!global.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(
            accountsMerge(
                ImmutableList.of(
                    ImmutableList.of("John", "johnsmith@mail.com", "john00@mail.com"),
                    ImmutableList.of("John", "johnnybravo@mail.com"),
                    ImmutableList.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    ImmutableList.of("Mary", "mary@mail.com"),
                    ImmutableList.of("Mary", "mary@mail.com")
                )
            ),
            ImmutableList.of(
                ImmutableList.of("John", "johnnybravo@mail.com"),
                ImmutableList.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                ImmutableList.of("Mary", "mary@mail.com")
            )
        );

        Assert.assertEquals(
            accountsMerge(
                ImmutableList.of(
                )
            ),
            ImmutableList.of(
            )
        );

        Assert.assertEquals(
            accountsMerge(
                ImmutableList.of(
                    ImmutableList.of("David", "David0@m.co", "David1@m.co"),
                    ImmutableList.of("David", "David3@m.co", "David4@m.co"),
                    ImmutableList.of("David", "David4@m.co", "David5@m.co"),
                    ImmutableList.of("David", "David2@m.co", "David3@m.co"),
                    ImmutableList.of("David", "David1@m.co", "David2@m.co")
                )
            ),
            ImmutableList.of(
                ImmutableList.of("David", "David0@m.co", "David1@m.co", "David2@m.co", "David3@m.co", "David4@m.co", "David5@m.co")
            )
        );
    }

    private record Vertex(String email) {
    }

    private static class UndirectedGraph {
        Map<Vertex, Set<Vertex>> edges = new HashMap<>();

        public void add(Vertex from, Vertex to) {
            var vertices1 = edges.computeIfAbsent(from, x -> new HashSet<>());
            vertices1.add(to);
            var vertices2 = edges.computeIfAbsent(to, x -> new HashSet<>());
            vertices2.add(from);
        }

        public void add(Vertex v) {
            edges.putIfAbsent(v, new HashSet<>());
        }

        public Set<Vertex> getAllVertices() {
            return edges.keySet();
        }

        public List<Vertex> getListOfNeighbours(Vertex v) {
            var vertices = edges.get(v);
            if (vertices == null) {
                return List.of();
            } else {
                return List.copyOf(vertices);
            }
        }
    }
}
