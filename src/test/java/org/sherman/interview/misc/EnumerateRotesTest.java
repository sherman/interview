package org.sherman.interview.misc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Denis Gabaydulin
 * @since 15/11/2016
 */
public class EnumerateRotesTest {
    @Test
    public void countRoutesBfs() {
        assertEquals(EnumerateRotes.countRoutesBfs(ImmutableMap.of(1, ImmutableList.of(2, 3), 2, ImmutableList.of(4, 5), 3, ImmutableList.of(5)), 1, 5), 2);
    }

    @Test
    public void countRoutes() {
        boolean[][] graph = new boolean[6][6];
        graph[1][2] = true;
        graph[1][3] = true;
        graph[2][4] = true;
        graph[2][5] = true;
        graph[3][5] = true;

        assertEquals(EnumerateRotes.countRoutes(graph, 1, 5), 2);
    }
}
