package org.sherman.interview.amazon;

/*
 * Copyright (C) 2019 by Denis M. Gabaydulin
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

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.testng.AssertJUnit.assertEquals;

public class Task1 {
    private static final Logger log = LoggerFactory.getLogger(Task1.class);

    @Test
    public void test() {
        int[] a = new int[]{1, 12, 42, 70, 36, -4, 43, 15};
        int[] b = new int[]{5, 15, 44, 72, 36, 2, 69, 24};

        assertEquals(solution(a, b), 5);
    }

    public int solution(int[] a, int[] b) {
        SortedSet<Pair> sorted = new TreeSet<>();

        for (int i = 0; i < a.length; i++) {
            Pair p = new Pair(a[i], b[i]);
            sorted.add(p);
        }

        int k = 0;
        /*sorted.forEach(
            s -> {
                log.info("{}", s);
            }
        );*/

        int l, r;

        Pair prev = null;
        for (Pair p : sorted) {
            if (prev == null) {
                k++;
            } else {
                if (prev.r < p.l) {
                    k++;
                }
            }
            prev = p;
        }

        return k;
    }

    private static class Pair implements Comparable<Pair> {
        private final int l;
        private final int r;

        private Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getL() {
            return l;
        }

        public int getR() {
            return r;
        }

        @Override
        public int compareTo(@NotNull Pair o) {
            return Comparator.comparingInt(Pair::getL)
                .thenComparing(Comparator.comparingInt(Pair::getR).reversed())
                .compare(this, o);
        }

        @Override
        public String toString() {
            return "Pair{" +
                "l=" + l +
                ", r=" + r +
                '}';
        }
    }
}
