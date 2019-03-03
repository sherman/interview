package org.sherman.interview.misc;

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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class GreedyOptimization {
    private static final Logger log = LoggerFactory.getLogger(GreedyOptimization.class);

    public static List<Result> getOptimalApps(List<App> priority1, List<App> priority2, int cpuQuota) {
        Map<Integer, App> hash = new HashMap<>();

        while (cpuQuota > 0) {
            for (int i = 0; i < priority1.size(); i++) {
                hash.put(priority1.get(i).cpu, priority1.get(i));
            }

            List<Result> results = new ArrayList<>();
            for (int j = 0; j < priority2.size(); j++) {
                if (hash.containsKey(cpuQuota - priority2.get(j).cpu)) {
                    results.add(new Result(hash.get(cpuQuota - priority2.get(j).cpu), priority2.get(j)));
                }
            }

            log.info("{}", results);

            if (!results.isEmpty()) {
                return results;
            }

            cpuQuota = cpuQuota - 1;
        }

        return ImmutableList.of();
    }

    @Test
    public void test1() {
        List<App> priority1 = Arrays.asList(new App(1, 2), new App(2, 4), new App(3, 3));
        List<App> priority2 = Arrays.asList(new App(1, 2), new App(2, 1), new App(3, 4));
        assertEquals(
            GreedyOptimization.getOptimalApps(priority1, priority2, 6),
            ImmutableList.of(
                new Result(new App(2, 4), new App(1, 2)),
                new Result(new App(1, 2), new App(3, 4))
            )
        );
    }

    @Test
    public void test2() {
        List<App> priority1 = Arrays.asList(new App(1, 10), new App(2, 7), new App(3, 5), new App(4, 3));
        List<App> priority2 = Arrays.asList(new App(1, 5), new App(2, 4), new App(3, 3), new App(4, 2));

        assertEquals(
            GreedyOptimization.getOptimalApps(priority1, priority2, 10),
            ImmutableList.of(
                new Result(new App(3, 5), new App(1, 5)),
                new Result(new App(2, 7), new App(3, 3))
            )
        );
    }

    @Test
        public void test3() {
            List<App> priority1 = Arrays.asList(new App(1, 1), new App(2, 4));
            List<App> priority2 = Arrays.asList(new App(1, 5), new App(2, 3), new App(3, 3), new App(4, 1));

            assertEquals(
                GreedyOptimization.getOptimalApps(priority1, priority2, 11),
                ImmutableList.of(
                    new Result(new App(2, 4), new App(1, 5))
                )
            );
        }

    private static class App implements Comparable<App> {
        private final int id;
        private final int cpu;

        private App(int id, int cpu) {
            this.id = id;
            this.cpu = cpu;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("cpu", cpu)
                .toString();
        }

        @Override
        public int compareTo(@NotNull App other) {
            return this.cpu - other.cpu;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            App that = (App) o;

            return Objects.equal(this.id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    private static class Result {
        private final App p1;
        private final App p2;

        private Result(App p1, App p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Result that = (Result) o;

            return Objects.equal(this.p1, that.p1) &&
                Objects.equal(this.p2, that.p2);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(p1, p2);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("p1", p1)
                .add("p2", p2)
                .toString();
        }
    }
}
